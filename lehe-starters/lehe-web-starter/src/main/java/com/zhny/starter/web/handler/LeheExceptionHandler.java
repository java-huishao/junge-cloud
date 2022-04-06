package com.zhny.starter.web.handler;

import com.zhny.starter.common.exception.BusinessExcpetion;
import com.zhny.starter.common.exception.GlobalException;
import com.zhny.starter.common.result.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;

public interface LeheExceptionHandler {

    Result handleIllegalParamException(MethodArgumentNotValidException e, HttpServletRequest request);

    Result handleException(Exception e, HttpServletRequest request);

    Result handleBusinessExcpetion(BusinessExcpetion e, HttpServletRequest request);

    Result handleGlobalException(GlobalException e, HttpServletRequest request);
}
