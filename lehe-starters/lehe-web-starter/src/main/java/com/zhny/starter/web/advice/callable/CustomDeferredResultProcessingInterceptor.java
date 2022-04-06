package com.zhny.starter.web.advice.callable;

import com.zhny.starter.common.result.Result;
import com.zhny.starter.common.result.ResultEnum;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;

/**
 * @program: mycloud-admin
 * @description:
 * @author: houqijun
 * @create: 2019-02-26 10:27
 **/
public class CustomDeferredResultProcessingInterceptor implements DeferredResultProcessingInterceptor {

    @Override
    public <T> boolean handleTimeout(NativeWebRequest request, DeferredResult<T> deferredResult) {
        deferredResult.setResult((T) Result.fail(ResultEnum.BUSINESS_TIME_OUT));
        return false;
    }

}
