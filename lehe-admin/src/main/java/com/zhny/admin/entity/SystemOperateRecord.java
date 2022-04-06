package com.zhny.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lehe.starter.mybatisplus.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author HOU
 * @since 2021-09-22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("system_operate_record")
public class SystemOperateRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 请求标题
     */
    private String title;
    /**
     * 日志类型
     */
    private Integer type;
    /**
     * 请求地址
     */
    private String requestUri;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 客户端来源
     */
    private String userAgent;
    /**
     * 服务id
     */
    private String serviceId;
    private String moduleName;
    private String exception;
    /**
     * 用户名
     */
    private String username;
    /**
     * 请求ip
     */
    private String ip;
    /**
     * 归属地
     */
    private String ipAddress;
    /**
     * 执行时间
     */
    private Integer costTime;
    private Integer status;


}
