package com.zhny.starter.common.result;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
public class Result<T> implements Serializable {

    private Integer code;
    private String message;
    private boolean success;
    private T data;
    private Map<String, Object> metaData;
    private String exception;
    private String tId;


    private Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getDesc();
    }

    private Result(Integer code, String desc, boolean success, T data) {
        this.code = code;
        this.message = desc;
        this.success = success;
        this.data = data;
    }

    private Result(Integer code, boolean success, String message) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    private Result(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getDesc();
        this.data = data;
    }

    public Result(Integer code, String message, boolean success, T data, Map<String, Object> metaData) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
        this.metaData = metaData;
    }

    public Result(Integer code, String message, boolean success, T data, Map<String, Object> metaData, String exception) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
        this.metaData = metaData;
        this.exception = exception;
    }

    public Result(Integer code, String message, boolean success, T data, Map<String, Object> metaData, String exception, String tId) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
        this.metaData = metaData;
        this.exception = exception;
        this.tId = tId;
    }

    //???????????????????????????
    public static <T> Result<T> success() {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), true, ResultEnum.SUCCESS.getDesc());
    }

    //????????????????????????
    public static <T> Result<T> success(T t) {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc(), true, t);
    }

    //???????????????
    public static <T> Result<T> success(ResultEnum resultEnum, T t) {
        return new Result<T>(resultEnum.getCode(), resultEnum.getDesc(), true, t);
    }

    //?????????????????????????????????
    public static <T> Result<T> fail() {
        return new Result<T>(ResultEnum.FAIL.getCode(), false, ResultEnum.FAIL.getDesc());
    }

    //????????????????????????
    public static <T> Result<T> fail(ResultEnum resultEnum) {
        return new Result<T>(resultEnum.getCode(), false, resultEnum.getDesc());
    }

    //?????????????????????
    public static <T> Result<T> fail(String message) {
        return new Result<>(ResultEnum.FAIL.getCode(), message, false, null);
    }

    //???????????????????????? ??? ??????
    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, message, false, null);
    }

    //?????????????????? ????????????
    public static <T> Result<T> fail(ResultEnum resultEnum, T t) {
        return new Result<>(resultEnum.getCode(), resultEnum.getDesc(), false, t);
    }

    //???????????????????????? ??? ??????
    public static <T> Result<T> fail(Integer code, T t) {
        return new Result<>(code, ResultEnum.FAIL.getDesc(), false, t);
    }
}
