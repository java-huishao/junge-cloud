package com.zhny.starter.web.inteceptors;

import cn.hutool.core.codec.Base64;
import com.zhny.starter.common.constant.SecurityConstants;
import com.zhny.starter.common.oauth2.Oauth2AccessUser;
import com.zhny.starter.common.utils.GsonUtil;
import com.zhny.starter.common.utils.SnowFlakeUtil;
import com.zhny.starter.common.utils.TenantContextHolder;
import com.zhny.starter.web.handler.TraceLogSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author houqj
 * 2019-08-07 16:44
 */
@Slf4j
@Component
public class UserSessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("在方法被调用前执行.....");

        MDC.put(TraceLogSender.trace_key_trace_id, String.valueOf(System.currentTimeMillis()));
        String userJson = request.getHeader(SecurityConstants.AUTH_HEADER);
        if (StringUtils.isBlank(request.getHeader(SecurityConstants.trace_id))) {
            MDC.put(TraceLogSender.trace_key_trace_id, SnowFlakeUtil.getId().toString());
            MDC.put(TraceLogSender.trace_key_parent_trace_id, "0");
        } else {
            MDC.put(TraceLogSender.trace_key_trace_id, SnowFlakeUtil.getId().toString());
            MDC.put(TraceLogSender.trace_key_parent_trace_id, request.getHeader(SecurityConstants.trace_id));
        }
        if (StringUtils.isNotBlank(userJson) && !"null".equals(userJson)) {
            Oauth2AccessUser oauth2AccessUser = GsonUtil.gsonToBean(userJson, Oauth2AccessUser.class);
            oauth2AccessUser.setName(Base64.decodeStr(oauth2AccessUser.getName()));
            TenantContextHolder.setUser(oauth2AccessUser);
        }
        return Boolean.TRUE;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("在方法执行后执行.....");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("在整个请求处理完毕后进行回调.....");
        TenantContextHolder.clear();
    }
}
