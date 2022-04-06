package com.zhny.admin.config;


import com.zhny.starter.cache.RedisUtil;
import com.zhny.starter.common.oauth2.Oauth2AccessUser;
import com.zhny.starter.common.utils.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author houqj
 * 2020-01-02 15:10
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 2)
public class UserSessionFilter extends GenericFilterBean {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String authentication = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNotBlank(authentication)) {
            String token = authentication.substring(7);
            Oauth2AccessUser user = redisUtil.get(token, Oauth2AccessUser.class);
            TenantContextHolder.setUser(user);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.debug("过滤器被销毁...");
    }
}
