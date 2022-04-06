package com.zhny.common.param;

import lombok.Data;

/**
 * @author houqj
 * 2021-02-08 14:08
 */
@Data
public class AccountRegiste {
    //企业名称
    private String companyName;
    //用户姓名
    private String userName;
    //职务
    private String duty;
    //用户密码
    private String password;
    //用户电话
    private String mobile;
    //验证码
    private String validCode;
}
