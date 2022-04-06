package com.zhny.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lehe.starter.mybatisplus.base.BaseParent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 后台权限表
 * </p>
 *
 * @author HOU
 * @since 2021-09-16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("admin_permission")
public class AdminPermission extends BaseParent<AdminPermission> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单路径
     */
    private String path;
    /**
     * vue加载路径
     */
    private String component;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单标题（英文）
     */
    private String title;
    /**
     * 菜单类型（-1.模块，0：菜单，1按钮）
     */
    private Integer type;
    /**
     * 外部链接
     */
    private String url;
    /**
     * 菜单级别
     */
    private Integer level;
    /**
     * 按钮类型
     */
    private String buttonType;
    /**
     * 按钮代码
     */
    private Integer status;
    /**
     * mate 里是否展示此菜单
     */
    private boolean showAlways;
    /**
     * 排序
     */
    private BigDecimal sortOrder;
    /**
     * 排序值
     */
    private boolean isMenu;
    /**
     * 是否是父节点
     */
    private boolean isParent;
    /**
     * 备注
     */
    private String description;


}
