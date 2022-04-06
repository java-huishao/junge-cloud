package com.lehe.annotations.mybatis;

import java.lang.annotation.*;

/**
 * @author 2020
 * 2021-10-19 12:35
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MetaData {

    /**
     * 数据权限所在的表名称
     *
     * @return
     */
    String datascopeTableName() default "";

    /**
     * 和当前用户关联的字段
     */
    String associatedUserId() default "";

    /**
     * 数据权限所在的列名称
     *
     * @return
     */
    String dataScopeColumnName() default "";
}
