package com.zhny.starter.log.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.zhny.starter.common.utils.TenantContextHolder;
import com.zhny.starter.log.annotation.SysLogAction;
import com.zhny.starter.log.annotation.SystemLog;
import com.zhny.starter.log.common.Content;
import com.zhny.starter.log.common.SysLog;
import com.zhny.starter.log.common.SysLogEntity;
import com.zhny.starter.log.enums.ModelType;
import com.zhny.starter.log.event.SysLogEvent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.io.InputStreamSource;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author houqj
 * 2021-01-20 11:26
 */
@Slf4j
@Aspect
@Component
public class SystemLogAspect {
    /**
     * 用于SpEL表达式解析.
     */
    private final SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
    /**
     * 用于获取方法参数定义名字.
     */
    private final DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();
    @Resource
    HttpServletRequest request;
    @Resource
    ApplicationContext applicationContext;
    @Value("${spring.application.name}")
    private String applicationName;

    public SystemLogAspect(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Pointcut("@annotation(com.lehe.starter.log.annotation.SystemLog)")
    public void actionPoint() {
    }

    @Around("actionPoint()")
    public Object doAfter(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;

        SystemLog systemLog = joinPoint.getTarget().getClass().getDeclaredAnnotation(SystemLog.class);
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        SysLog sysLog = new SysLog();
        SysLogAction sysLogAction = targetMethod.getAnnotation(SysLogAction.class);
        String requestURI = request.getRequestURI();
        String method1 = request.getMethod();
        String agent = request.getHeader("user-agent");
        if (sysLogAction == null) {
            return joinPoint.proceed();
        }
        List<SysLogEntity> logEntityList = new ArrayList<>();
        SysLogEntity sysLogEntity = getLog(joinPoint);

        sysLogEntity.setModule(sysLogAction.model().getLabel());
        sysLogEntity.setModuleName(sysLogAction.model().getName());
        sysLogEntity.setUri(requestURI);
        sysLogEntity.setMethod(method1);
        sysLogEntity.setAgent(agent);
        sysLogEntity.setUserId(TenantContextHolder.getUser().getId());
        sysLogEntity.setAccount(TenantContextHolder.getUser().getAccount());
        sysLogEntity.setOpenTime(TenantContextHolder.getUser().getOpenTime());
        sysLogEntity.setRoleName(TenantContextHolder.getUser().getRoleName());
        sysLogEntity.setActionType(sysLogAction.action().getName());
        sysLogEntity.setAction(sysLogAction.action().getName());
        sysLogEntity.setActionTime(DateUtil.formatDateTime(new Date()));
        if (StrUtil.isNotEmpty(sysLogAction.appName())) {
            sysLogEntity.setAppName(sysLogAction.appName());
        } else {
            sysLogEntity.setAppName(applicationName);
        }
        if (sysLogAction.model() != ModelType.NULL) {
            sysLogEntity.setAppName(sysLogAction.model().getName());
            sysLogEntity.setModule(sysLogAction.model().getLabel());
        } else if (systemLog != null) {
            sysLogEntity.setAppName(systemLog.model().getName());
            sysLogEntity.setModule(systemLog.model().getLabel());
        }
        try {
            if (sysLogAction.isReturn()) {
                logEntityList.add(sysLogEntity);
            } else {
                Object[] args = null;
                if (StrUtil.isNotEmpty(sysLogAction.targetObject()) && StrUtil.isNotEmpty(sysLogAction.detail())) {
                    String object = sysLogAction.targetObject();
                    String detail = sysLogAction.detail();

                    if (object.contains("#")) {
                        //获取方法参数值
                        args = joinPoint.getArgs();
                        object = getValBySpEL(object, methodSignature, args);
                    }
                    if (detail.contains("#")) {
                        //获取方法参数值
                        args = joinPoint.getArgs();
                        detail = getValBySpEL(detail, methodSignature, args);
                    }
                    sysLogEntity.setActionDetail(detail);
                    sysLogEntity.setTargetObject(object);
                    logEntityList.add(sysLogEntity);
                } else {
                    Method method = methodSignature.getMethod();
                    Class proxyClass = systemLog.logClass();
                    Method logMethod = proxyClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
                    Object object = proxyClass.newInstance();
                    Object logInvoke = logMethod.invoke(object, joinPoint.getArgs());
                    logEntityList.addAll(transferLogEntity(logInvoke, sysLogEntity, sysLogAction));
                }
                applicationContext.publishEvent(new SysLogEvent(this, logEntityList));
                obj = joinPoint.proceed();
                return obj;
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("日志记录异常:{}", e.getMessage());
        }
        return obj;
    }

    @SuppressWarnings("unchecked")
    private List<SysLogEntity> transferLogEntity(Object obj, SysLogEntity sysLogEntity, SysLogAction sysLogAction) {
        List<SysLogEntity> sysLogEntityList = new ArrayList<>();
        if (obj instanceof Content) {
            Content content = (Content) obj;
            sysLogEntity.setActionDetail(content.getDetail());
            sysLogEntity.setTargetObject(content.getObject());
            if (content.getOperateEnum() == null) {
                sysLogEntity.setAction(sysLogAction.action().getName());
            } else {
                sysLogEntity.setAction(content.getOperateEnum().getName());
            }
            if (StrUtil.isNotEmpty(content.getSubModel())) {
                sysLogEntity.setAppName(content.getSubModel());
            }
            sysLogEntityList.add(sysLogEntity);
        } else if (obj instanceof Collection) {
            List<Content> contentList = (List) obj;
            for (Content content : contentList) {
                SysLogEntity sysLogEntity1 = new SysLogEntity();
                BeanUtils.copyProperties(sysLogEntity, SysLogEntity.class);
                sysLogEntity1.setActionDetail(content.getDetail());
                sysLogEntity1.setTargetObject(content.getObject());
                if (content.getOperateEnum() == null) {
                    sysLogEntity1.setAction(sysLogAction.action().getName());
                } else {
                    sysLogEntity1.setAction(content.getOperateEnum().getName());
                }
                if (StrUtil.isNotEmpty(content.getSubModel())) {
                    sysLogEntity.setAppName(content.getSubModel());
                }
                sysLogEntity.setUserId(TenantContextHolder.getUser().getId());
                sysLogEntity.setActionType(sysLogAction.action().getName());
                sysLogEntity.setAccount(TenantContextHolder.getUser().getAccount());
                sysLogEntity.setOpenTime(TenantContextHolder.getUser().getOpenTime());
                sysLogEntity.setRoleName(TenantContextHolder.getUser().getRoleName());
                sysLogEntityList.add(sysLogEntity1);
            }
        }
        return sysLogEntityList;
    }

    /**
     * 构建日志对象
     */
    private SysLogEntity getLog(JoinPoint joinPoint) {
        SysLogEntity sysLogEntity = new SysLogEntity();
        Object[] args = joinPoint.getArgs();
        List<Object> objectList = new ArrayList<>(args.length);
        for (Object arg : args) {
            boolean parse = parse(arg);
            if (parse) {
                objectList.add(arg);
            }
        }
        sysLogEntity.setArgs(JSON.toJSONString(objectList, new JsonFilter()));
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        sysLogEntity.setClassName(methodSignature.getDeclaringTypeName());
        sysLogEntity.setMethodName(methodSignature.getName());
        String ipAddress = ServletUtil.getClientIP(request);
        sysLogEntity.setIp(ipAddress);
        sysLogEntity.setAccount(TenantContextHolder.getUser().getAccount());
        sysLogEntity.setOpenTime(TenantContextHolder.getUser().getOpenTime());
        sysLogEntity.setRoleName(TenantContextHolder.getUser().getRoleName());
        return sysLogEntity;
    }

    /**
     * 数据是否可以序列化
     *
     * @param object
     * @return Result 返回结果 true
     */
    private boolean parse(Object object) {
        if (object instanceof HttpServletRequest) {
            return false;
        } else if (object instanceof HttpServletResponse) {
            return false;
        } else if (object instanceof InputStreamSource) {
            return false;
        } else {
            return !(object instanceof OutputStream);
        }
    }

    /**
     * 解析spEL表达式
     */
    private String getValBySpEL(String spEL, MethodSignature methodSignature, Object[] args) {
        //获取方法形参名数组
        String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
        if (paramNames != null && paramNames.length > 0) {
            Expression expression = spelExpressionParser.parseExpression(spEL);
            // spring的表达式上下文对象
            EvaluationContext context = new StandardEvaluationContext();
            // 给上下文赋值
            for (int i = 0; i < args.length; i++) {
                context.setVariable(paramNames[i], args[i]);
            }
            return Objects.requireNonNull(expression.getValue(context)).toString();
        }
        return null;
    }

    private class JsonFilter implements PropertyFilter {

        /**
         * @param object the owner of the property
         * @param name   the name of the property
         * @param value  the value of the property
         * @return Result 返回结果 true if the property will be included, false if to be filtered out
         */
        @Override
        public boolean apply(Object object, String name, Object value) {
            return parse(object) && parse(value);
        }
    }
}
