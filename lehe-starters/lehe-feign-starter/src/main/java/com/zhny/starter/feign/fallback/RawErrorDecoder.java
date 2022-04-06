package com.zhny.starter.feign.fallback;

import feign.Response;
import feign.RetryableException;
import feign.Util;
import feign.codec.ErrorDecoder;

import java.io.IOException;

/**
 * @author 2020
 * 2021-09-24 14:48
 */
public class RawErrorDecoder extends ErrorDecoder.Default {
    @Override
    public Exception decode(String methodKey, Response response) {
        Exception exception = super.decode(methodKey, response);
        if (exception instanceof RetryableException) {
            return exception;
        }
        try {
            if (response.body() != null) {
                String message = Util.toString(response.body().asReader(Util.UTF_8));
                return new RuntimeException(message);
            }
        } catch (IOException ignored) {
        }
        return exception;
    }
}
