package com.lehe.starter.mybatisplus.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseParam implements Serializable {

    private String tenantId;
    private String createrId;
    private String sort;
    private String order;
    private String startTime;
    private String endTime;
    private Long current;
    private Long size;
}
