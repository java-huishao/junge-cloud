package com.zhny.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lehe.starter.mybatisplus.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 后台角色表
 * </p>
 *
 * @author HOU
 * @since 2021-09-16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("admin_role")
public class AdminRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色code
     */
    private String roleCode;
    /**
     * 是否默认(1是，0否)
     */
    private Integer def;
    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private List<String> pids;


}
