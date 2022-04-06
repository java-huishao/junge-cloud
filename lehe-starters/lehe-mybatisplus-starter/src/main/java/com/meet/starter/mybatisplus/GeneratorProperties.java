package com.lehe.starter.mybatisplus;

import lombok.Data;

/**
 * @author 2020
 * @date 2021-12-21 10:36
 */
@Data
public class GeneratorProperties {
    private String author;
    private String workSpace;
    private String moduleName;
    private String packageName;
    private String driverName;
    private String url;
    private String username;
    private String password;
    private String[] tables;
}
