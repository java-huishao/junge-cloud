package com.lehe.starter.mybatisplus.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 2020
 * 2021-10-18 14:56
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataScope {
    /**
     * 当前功能的authKey，根据authKey去查询当前用户的具体权限值
     */
    String authKey() default "";

    /**
     * 需要加数据权限范围的表别名,单位权限
     */
    String comTableName() default "com";

    String comFieldName() default "com_id";
}
