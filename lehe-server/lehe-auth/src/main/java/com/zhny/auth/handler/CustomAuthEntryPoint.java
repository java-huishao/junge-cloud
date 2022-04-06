package com.zhny.auth.handler;

import com.zhny.auth.util.ResponseUtil;
import com.zhny.starter.common.result.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mycloud
 * com.lehe.auth.oauth2server.handler
 * @Description: UnauthorizedHandler
 * 2018/4/19 10:22
 */
@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {
    /**
     * 自定义EntryPoint用于tokan校验失败返回信息
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        //返回json形式的错误信息
        ResponseUtil.out(response, Result.fail("token校验失败"));
    }
}
