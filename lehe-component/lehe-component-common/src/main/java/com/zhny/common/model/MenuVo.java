package com.zhny.common.model;

import lombok.Data;

import java.util.List;

@Data
public class MenuVo {

    private String id;
    private String name;
    private String path;
    private String component;
    private String parentId;
    private Meta meta;
    private List<MenuVo> children;
}
