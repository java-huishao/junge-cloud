package com.lehe.starter.mybatisplus.base;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author houqj
 * 2021-02-01 10:52
 */
@Data
@ToString(callSuper = true)
public class BaseParent<T> extends BaseEntity {
    /**
     * 菜单父id
     */
    private Long parentId;

    @TableField(exist = false)
    private List<T> children;
}
