package com.zhny.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lehe.starter.mybatisplus.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登录背景配置表
 * </p>
 *
 * @author HOU
 * @since 2021-09-22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("system_login_config")
public class SystemLoginConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 背景视频
     */
    private String bgVideo;
    /**
     * 背景webm
     */
    private String bgWebm;
    /**
     * 背景图片
     */
    private String bgImg;
    /**
     * 是否是背景
     */
    private Integer isBg;


}
