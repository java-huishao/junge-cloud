package com.zhny.common.constant;

/**
 *
 */
public interface SmsConstant {
    //验证码 存入redis中
    String VALID_CODE = "smsValidCode::";
    //验证码长度
    int VALID_CODE_LENGTH = 6;
    //验证码过期时间 单位（秒）
    int VALID_CODE_EXPIRE_TIME = 120;


}
