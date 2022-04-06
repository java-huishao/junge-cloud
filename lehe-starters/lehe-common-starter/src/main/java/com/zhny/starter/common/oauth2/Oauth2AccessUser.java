package com.zhny.starter.common.oauth2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 2020
 * 2021-10-19 10:01
 * 全局用户session信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Oauth2AccessUser extends OAuth2AccessToken {
    //用户id
    private Long id;
    //租户id
    private String tenantId;
    //登录账号
    private String account;
    //姓名
    private String name;
    //扩展字段-开通时间
    private String openTime;
    //扩展字段-用户类型
    private String userType;
    //扩展字段-角色名称
    private String roleName;
    //扩展字段-用户角色code集合
    private List<String> roleCodes;
    //扩展字段-用户权限按钮集合
    private List<String> btns;

    // more custom extension.....
}
