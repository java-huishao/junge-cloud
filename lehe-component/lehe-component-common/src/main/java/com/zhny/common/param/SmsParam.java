package com.zhny.common.param;

import lombok.Data;

/**
 * @author houqj
 * 2021-01-27 10:06
 */
@Data
public class SmsParam {
    //手机号
    private String mobile;
    //类型
    private String type;
    //验证码
    private String validCode;
}
