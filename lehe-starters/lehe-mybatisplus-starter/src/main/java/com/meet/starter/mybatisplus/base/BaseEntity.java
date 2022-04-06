package com.lehe.starter.mybatisplus.base;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.diboot.core.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @author Administrator
 * com.lehe.entity
 * @Description: 基础实体类
 * 2018/4/12 14:00
 */
@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class BaseEntity extends AbstractEntity<Long> implements Serializable {

    /*主键id*/
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /*创建人id*/
    @TableField(value = "creater_id", fill = FieldFill.INSERT)
    protected String createrId;

    /*创建人姓名*/
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    protected String createBy;

    /*创建时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    protected String createTime;

    /*修改人id*/
    @TableField(value = "updater_id", fill = FieldFill.UPDATE)
    protected String updaterId;

    /*修改人姓名*/
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    protected String updateBy;

    /*修改时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    protected String updateTime;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
