package com.zhny.common.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author houqj
 * 2021-02-17 12:45
 */
@Data
public class ModelFieldPO {

    private String id;

    private String fieldName;

    private String formType;

    private String name;

    private Integer type;

    private String inputTips;

    private Integer maxLength;

    private Object defaultValue;

    private Integer isUnique;

    private Integer isNull;

    private String options;

    private Integer fieldType;

    private List<Object> setting = new ArrayList<>();
}
