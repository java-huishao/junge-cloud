package com.zhny.starter.web.advice;

import com.alibaba.fastjson.JSON;
import com.zhny.starter.common.exception.BusinessExcpetion;
import com.zhny.starter.common.exception.GlobalException;
import com.zhny.starter.common.result.Result;
import com.zhny.starter.web.handler.LeheExceptionHandler;
import com.zhny.starter.web.handler.TraceLogSender;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 描述: controller 层异常处理
 *
 * @author houqj
 * 2019/7/24
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice implements ResponseBodyAdvice, LeheExceptionHandler {

    private final ThreadLocal<Object> modelHolder = new ThreadLocal<>();

    @Resource
    private LeheExceptionHandler exceptionHandler;


    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleIllegalParamException(MethodArgumentNotValidException e, HttpServletRequest request) {
        return exceptionHandler.handleIllegalParamException(e, request);
    }

    @Override
    @ExceptionHandler(BusinessExcpetion.class)
    public Result handleBusinessExcpetion(BusinessExcpetion e, HttpServletRequest request) {
        return exceptionHandler.handleBusinessExcpetion(e, request);
    }

    @Override
    @ExceptionHandler(GlobalException.class)
    public Result handleGlobalException(GlobalException e, HttpServletRequest request) {
        return exceptionHandler.handleGlobalException(e, request);
    }

    @Override
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e, HttpServletRequest request) {
        return exceptionHandler.handleException(e, request);
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        // ModelHolder 初始化
        modelHolder.set(webDataBinder.getTarget());
        MDC.put(TraceLogSender.trace_key_post_param, JSON.toJSONString(webDataBinder.getTarget()));
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return o;
    }
}
