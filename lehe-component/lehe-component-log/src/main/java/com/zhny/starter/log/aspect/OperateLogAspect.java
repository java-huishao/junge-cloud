package com.zhny.starter.log.aspect;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.zhny.starter.log.annotation.AddDataRecord;
import com.zhny.starter.log.annotation.ModifyDataRecord;
import com.zhny.starter.log.annotation.OperateDataRecord;
import com.zhny.starter.log.utils.SpELUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * @author houqj
 * 2021-04-08 16:22
 */
@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Resource
    ApplicationContext applicationContext;
    @Resource
    private HttpServletRequest request;

    public OperateLogAspect(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 定义一个方法, 用于声明切入点表达式. 一般地, 该方法中再不需要添入其他的代码.
     * 使用 @Pointcut 来声明切入点表达式.
     * 后面的其他通知直接使用方法名来引用当前的切入点表达式.
     * （..）表示任意参数
     */
    @Pointcut("@annotation(com.lehe.starter.log.annotation.ModifyDataRecord)")
    public void modifyDateRecord() {
    }

    @Pointcut("@annotation(com.lehe.starter.log.annotation.AddDataRecord)")
    public void addDataRecord() {
    }

    @Pointcut("@annotation(com.lehe.starter.log.annotation.OperateDataRecord)")
    public void operateDataRecord() {
    }

    //声明该方法执行之前执行，前置通知
    //直接导入切面点，上面的
    @Before("modifyDateRecord()")
    public void beforeMethod(JoinPoint joinPoint) throws IllegalAccessException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ModifyDataRecord annotation = method.getAnnotation(ModifyDataRecord.class);
        String module = annotation.module();
        String id = SpELUtil.getValBySpEL(annotation.id(), methodSignature, joinPoint.getArgs());
        log.info("操作模块:{}", module);
        log.info("操作id:{}", id);
    }

    /**
     * 后置返回通知
     * 这里需要注意的是:
     * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，
     * 对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     *
     * @param joinPoint
     * @param keys
     */
    @AfterReturning(value = "addDataRecord()", returning = "keys")
    public void doAfterReturningAdviceWhthAddDataRecord(JoinPoint joinPoint, Object keys) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        AddDataRecord annotation = method.getAnnotation(AddDataRecord.class);
        String idSpel = annotation.id();
        String actionName = annotation.actionName();
        String module = annotation.module();
        String moduleNameSpel = annotation.moduleName();
        String id = SpELUtil.getValBySpEL(idSpel, methodSignature, joinPoint.getArgs());
        String moduleName = SpELUtil.getValBySpEL(moduleNameSpel, methodSignature, joinPoint.getArgs());
        //构建基础信息实体类并保存
    }

    @AfterReturning(value = "operateDataRecord()", returning = "result")
    public void doAfterReturningAdviceWithOperateDataRecord(JoinPoint joinPoint, Object result) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        OperateDataRecord annotation = method.getAnnotation(OperateDataRecord.class);
        String idSpel = annotation.id();
        String actionName = annotation.actionName();
        String module = annotation.module();
        int operateType = annotation.operateType();
        String id = SpELUtil.getValBySpEL(idSpel, methodSignature, joinPoint.getArgs());
        //构建基础信息实体类并保存

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
        } else {
            return !(object instanceof OutputStream);
        }
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
