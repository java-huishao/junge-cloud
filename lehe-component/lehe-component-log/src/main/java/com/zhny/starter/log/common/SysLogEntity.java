package com.zhny.starter.log.common;

import lombok.Data;

@Data
public class SysLogEntity {


    private Integer module;

    private String moduleName;

    private String className;


    private String methodName;


    private String args;


    private String appName;


    private String targetObject;


    private String action;


    private String actionDetail;


    private String ip;

    private Long userId;

    private String roleName;

    private String account;

    private String openTime;

    private String actionType;

    private String actionTime;

    private String uri;

    private String method;

    private String agent;

}
