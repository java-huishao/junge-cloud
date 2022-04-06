package com.zhny.common.model;

import lombok.Data;

import java.util.List;

/**
 * @author houqj
 * 2020-01-07 10:51
 */
@Data
public class AuthAccount {
    /**
     * 主键
     */
    private String id;
    /**
     * 主键
     */
    private String tenantId;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 姓名
     */
    private String deptName;
    /**
     * 密码
     */
    private String password;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 开通时间
     */
    private String createTime;
    /**
     * 账户类型
     */
    private Integer type;
    /**
     * 账户是否启用
     */
    private boolean enabled;
    /**
     * 账户没有超时
     */
    private boolean accountNonExpired = true;
    /**
     * 账户是否被锁定
     */
    private boolean accountNonLocked = true;
    /**
     * 凭证是否超时
     */
    private boolean credentialsNonExpired = true;

    private List<String> btns;

    private List<String> roles;

    private Boolean admin;
}
