package com.zhny.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lehe.starter.mybatisplus.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 系统字典分类表
 * </p>
 *
 * @author HOU
 * @since 2021-09-22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("system_dictionary")
public class SystemDictionary extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典key
     */
    private String code;
    /**
     * 字典value
     */
    private String name;
    /**
     * 排序
     */
    private BigDecimal sort;
    /**
     * 类型
     */
    private String type;
    /**
     * 备注
     */
    private String remark;


}
