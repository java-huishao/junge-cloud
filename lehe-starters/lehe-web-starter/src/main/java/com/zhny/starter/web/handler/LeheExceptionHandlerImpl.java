package com.zhny.starter.web.handler;

import com.alibaba.fastjson.JSON;
import com.zhny.starter.common.entity.ValidParamMessage;
import com.zhny.starter.common.exception.BusinessExcpetion;
import com.zhny.starter.common.exception.GlobalException;
import com.zhny.starter.common.result.Result;
import com.zhny.starter.common.result.ResultEnum;
import com.zhny.starter.common.utils.GsonUtil;
import com.zhny.starter.web.utils.ThrowableUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author houqj
 * 2020-04-17 11:46
 */
@Slf4j
@Component
public class LeheExceptionHandlerImpl implements LeheExceptionHandler {

    @Resource
    private TraceLogSender traceLogSender;

    @Override
    public Result handleIllegalParamException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("【【进入异常处理】】，参数验证错误:{},参数==>{}", e.getMessage(), e.getParameter());
        Map<String, List<String>> list = e.getBindingResult().getFieldErrors().stream().collect(Collectors.groupingBy(FieldError::getField))
                .entrySet().stream().map(s -> {
                    return new ValidParamMessage(s.getKey(), s.getValue().stream().map(m -> m.getDefaultMessage()).collect(Collectors.toList()));
                }).collect(Collectors.toMap(ValidParamMessage::getFieldName, ValidParamMessage::getMessage));
        Result result = Result.builder()
                .code(ResultEnum.BAD_PARAM.getCode())
                .message(ResultEnum.BAD_PARAM.getDesc() + JSON.toJSONString(list))
                .exception(ThrowableUtils.toString(e))
                .build();
        MDC.put(TraceLogSender.trace_key_result, GsonUtil.gson2String(result));
        asyncSendTraceLog();
        return result;
    }

    @Override
    public Result handleException(Exception e, HttpServletRequest request) {
        log.error("【【进入异常处理】】,系统异常:{},信息==>{}", ThrowableUtils.toString(e));
        Result result = Result.builder()
                .code(ResultEnum.FAIL.getCode())
                .message(ResultEnum.FAIL.getDesc())
                .exception(ThrowableUtils.toString(e))
                .build();
        MDC.put(TraceLogSender.trace_key_result, GsonUtil.gson2String(result));
        asyncSendTraceLog();
        return result;
    }

    @Override
    public Result handleBusinessExcpetion(BusinessExcpetion e, HttpServletRequest request) {
        log.error("【【进入异常处理】】,自定义业务异常:{},信息==>{}", e.getMessage(), e.getCause());
        Result result = Result.builder()
                .code(ResultEnum.BUSINESS_ERROR.getCode())
                .message(ResultEnum.BUSINESS_ERROR.getDesc())
                .exception(ThrowableUtils.toString(e))
                .build();
        MDC.put(TraceLogSender.trace_key_result, GsonUtil.gson2String(result));
        asyncSendTraceLog();
        return result;
    }

    @Override
    public Result handleGlobalException(GlobalException e, HttpServletRequest request) {
        log.error("【【进入异常处理】】,全局异常:{},信息==>{}", e.getMessage(), e.getCause());
        Result result = Result.builder()
                .code(ResultEnum.SERVER_ERROR.getCode())
                .message(ResultEnum.SERVER_ERROR.getDesc())
                .exception(ThrowableUtils.toString(e))
                .build();
        MDC.put(TraceLogSender.trace_key_result, GsonUtil.gson2String(result));
        asyncSendTraceLog();
        return result;
    }

    private void asyncSendTraceLog() {
        traceLogSender.sendTraceLog();
    }
}
