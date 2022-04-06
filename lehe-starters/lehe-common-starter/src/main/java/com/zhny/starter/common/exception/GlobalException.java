package com.zhny.starter.common.exception;

import com.zhny.starter.common.result.ResultEnum;
import lombok.Data;

@Data
public class GlobalException extends RuntimeException {

    private ResultEnum resultEnum;

    public GlobalException(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}
