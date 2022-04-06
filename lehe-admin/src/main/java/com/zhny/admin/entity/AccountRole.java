package com.zhny.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lehe.starter.mybatisplus.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台账号角色中间表
 * </p>
 *
 * @author HOU
 * @since 2021-09-16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("account_role")
public class AccountRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账号id
     */
    private String accountId;
    /**
     * 角色id
     */
    private String roleId;


}
