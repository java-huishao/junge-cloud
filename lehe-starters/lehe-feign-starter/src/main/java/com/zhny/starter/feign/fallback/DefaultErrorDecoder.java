package com.zhny.starter.feign.fallback;

import com.alibaba.fastjson.JSONObject;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

/**
 * @author 2020
 * 2021-09-24 14:39
 * https://blog.csdn.net/hkk666123/article/details/115290796
 */
public class DefaultErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        String message = null;
        try {
            if (response.body() != null) {
                message = Util.toString(response.body().asReader(Util.UTF_8));
                JSONObject json = JSONObject.parseObject(message);
                return new RuntimeException(json.getString("message"));
            }
        } catch (Exception ignored) {
        }
        return new RuntimeException(message);
    }
}
