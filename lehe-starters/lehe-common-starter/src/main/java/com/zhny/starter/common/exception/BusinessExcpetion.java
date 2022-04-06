package com.zhny.starter.common.exception;


import com.zhny.starter.common.result.ResultEnum;

/**
 * @author huangqi
 * com.lehe.admin.excepiton
 * @Description: 自定义业务异常
 * 2018/7/3 16:09
 */
public class BusinessExcpetion extends RuntimeException {
    static final long serialVersionUID = 1L;
    private Integer code;

    public BusinessExcpetion(String message) {
        super(message);
        this.code = ResultEnum.FAIL.getCode();
    }

    public BusinessExcpetion(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessExcpetion(ResultEnum statusEnum) {
        super(statusEnum.getDesc());
        this.code = statusEnum.getCode();
    }

    public BusinessExcpetion(Integer code, String message, Object... args) {
        super(String.format(message, args));
        this.code = code;
    }

    public BusinessExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
