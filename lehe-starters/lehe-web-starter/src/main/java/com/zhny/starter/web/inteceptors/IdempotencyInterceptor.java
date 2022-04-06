package com.zhny.starter.web.inteceptors;

import cn.hutool.core.util.StrUtil;
import com.zhny.starter.common.annotations.ApiIdempotent;
import com.zhny.starter.common.constant.IdempotencyVersionNumber;
import com.zhny.starter.common.result.Result;
import com.zhny.starter.common.result.ResultEnum;
import com.zhny.starter.web.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author houqj
 * 2022年3月18日10:50:01
 * 幂等性校验拦截器
 */
@Slf4j
@Component
public class IdempotencyInterceptor implements HandlerInterceptor {

    private static String VERSION_KEY="version";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        ApiIdempotent methodAnnotation = method.getAnnotation(ApiIdempotent.class);
        if (methodAnnotation != null) {
            //check(request);// 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
        }
        if (RequestMethod.POST.equals(method.getName())) {
            return check(request, response);
        }
        return Boolean.TRUE;
    }

    private boolean check(HttpServletRequest request, HttpServletResponse response) {
        String version = request.getParameter(VERSION_KEY);
        if (StrUtil.isNotBlank(version)) {
            //如果不存在key，则需要获取key
            if (!IdempotencyVersionNumber.versionMap.containsKey(version)) {
                ResponseUtil.out(response, Result.fail(ResultEnum.Idempotency_version_valid_error));
                return Boolean.FALSE;
            }
            //如果存在key，并且value已经是此版本号了，则是重复请求
            if (IdempotencyVersionNumber.versionMap.get(version).equals(version)) {
                ResponseUtil.out(response, Result.fail(ResultEnum.Idempotency_version_dont_repeat_request));
                return Boolean.FALSE;
            }
            IdempotencyVersionNumber.versionMap.put(version, version);
        }
        return Boolean.TRUE;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String version = request.getParameter(VERSION_KEY);
        if (StrUtil.isNotBlank(version)) {
            IdempotencyVersionNumber.versionMap.remove(version);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
