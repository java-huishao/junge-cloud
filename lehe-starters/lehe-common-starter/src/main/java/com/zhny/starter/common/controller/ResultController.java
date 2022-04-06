package com.zhny.starter.common.controller;


import com.zhny.starter.common.result.Result;
import com.zhny.starter.common.result.ResultEnum;

/**
 * @author houqj
 * 2020-01-02 16:09
 */
public abstract class ResultController {

    protected Result SUCCESS() {
        return Result.success();
    }

    protected Result SUCCESS(Object o) {
        return Result.success(o);
    }

    protected Result FAIL(String message) {
        return Result.fail(message);
    }

    protected Result FAIL(ResultEnum resultEnum) {
        return Result.fail(resultEnum);
    }
}
