package com.zhny.common.param;

import lombok.Data;

/**
 * @author houqj
 * 2021-02-08 12:45
 */
@Data
public class AccountResetPassword {
    private String id;
    private String mobile;
    private String password;
    //验证码
    private String validCode;
}
