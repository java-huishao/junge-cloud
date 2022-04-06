package com.lehe.starter.mybatisplus;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lehe.starter.mybatisplus.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author houqj
 * @since 2021-12-27 16:12:42
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("dictionary")
public class Dictionary extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父ID
     */
    private Long parentId;
    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 应用模块
     */
    private String appModule;
    /**
     * 字典类型
     */
    private String type;
    /**
     * 显示名
     */
    private String itemName;
    /**
     * 存储值
     */
    private String itemValue;
    /**
     * 描述说明
     */
    private String description;
    /**
     * 扩展JSON
     */
    private String extdata;
    /**
     * 排序号
     */
    private Integer sortId;
    /**
     * 是否可改
     */
    @TableField("is_editable")
    private Boolean editable;
    /**
     * 是否可删
     */
    @TableField("is_deletable")
    private Boolean deletable;
    /**
     * 删除标记
     */
    @TableField("is_deleted")
    private Boolean deleted;


}
