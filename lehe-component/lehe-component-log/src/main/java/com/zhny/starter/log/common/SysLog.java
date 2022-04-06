package com.zhny.starter.log.common;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author HOU
 * @since 2019-03-03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SysLog {

    private static final long serialVersionUID = 1L;

    /**
     * 账号id
     */

    private String userId;
    /**
     * 账户名
     */

    private String account;
    /**
     * 角色名
     */

    private String roleName;
    /**
     * 开通时间
     */

    private String openTime;
    /**
     * 操作类型
     */

    private String actionType;
    /**
     * 操作时间
     */

    private String actionTime;
    /**
     * 操作对象
     */

    private String actionTarget;
    /**
     * 操作目标id
     */

    private String targetId;
    /**
     * 操作目标表名称
     */

    private String targetTableName;
    /**
     * 操作详情
     */

    private String actionDetail;
    /**
     * 请求参数
     */

    private String param;
    /**
     * 请求ip
     */

    private String ip;
    /**
     * 请求路径
     */

    private String uri;
    /**
     * 请求方法
     */

    private String method;
    /**
     * 浏览器
     */

    private String agent;
    /**
     * 服务id
     */

    private String serviceId;
    /**
     * 服务名称
     */

    private String appName;
    /**
     * 执行时间
     */

    private Long costTime;
    /**
     * 请求状态(1：成功，0：失败)
     */

    private Integer status;
    /**
     * 异常信息
     */

    private String exception;


}
