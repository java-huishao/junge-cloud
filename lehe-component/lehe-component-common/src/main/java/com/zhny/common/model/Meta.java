package com.zhny.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meta {
    private String title;
    private String icon;
    private boolean hideInMenu;
    private boolean showAlways;
    private boolean notCache;
    private String access;
    private String href;
    private List<String> roles;
}
