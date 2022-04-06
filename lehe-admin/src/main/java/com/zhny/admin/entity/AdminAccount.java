package com.zhny.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lehe.starter.mybatisplus.base.BaseEntity;
import com.zhny.starter.web.sensitive.Sensitive;
import com.zhny.starter.web.sensitive.SensitiveStrategy;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台账户表
 * </p>
 *
 * @author HOU
 * @since 2021-09-27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("admin_account")
public class AdminAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 登录账号
     */
    private String account;
    /**
     * 姓名
     */
    @Sensitive(strategy = SensitiveStrategy.USERNAME)
    private String userName;
    /**
     * 密码
     */
    @Sensitive(strategy = SensitiveStrategy.PASSWORD)
    private String password;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 部门id
     */
    private String deptId;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    private String gender;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 账户是否启用(1:启用，0：禁用)
     */
    private String enabled;
    /**
     * 账户是否过期
     */
    private Integer accountNonExpired;
    /**
     * 账户是否被锁定
     */
    private Integer accountNonLocked;
    /**
     * 凭证是否超时
     */
    private Integer credentialsNonExpired;
    /**
     * 在线状态(1:在线，0:离线)
     */
    private Integer online;
}
