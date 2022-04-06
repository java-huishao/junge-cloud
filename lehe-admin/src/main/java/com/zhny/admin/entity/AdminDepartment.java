package com.zhny.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lehe.starter.mybatisplus.base.BaseParent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统部门表
 * </p>
 *
 * @author HOU
 * @since 2021-09-22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("admin_department")
public class AdminDepartment extends BaseParent<AdminDepartment> {

    private static final long serialVersionUID = 1L;

    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 部门主管id
     */
    private String leader;
    /**
     * 排序编号
     */
    private Integer sort;
    /**
     * 是否启用
     */
    private Integer enabled;
    /**
     * 是否为父级节点
     */
    private Boolean isParent;


}
