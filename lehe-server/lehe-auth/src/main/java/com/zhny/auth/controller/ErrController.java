package com.zhny.auth.controller;

import com.zhny.starter.common.result.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author houqj
 * 2019-08-07 16:08
 */
@Controller
public class ErrController implements ErrorController {


    @RequestMapping("/error")
    public Result handleError(HttpServletRequest request) {
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        return Result.fail("系统错误");
    }
}
