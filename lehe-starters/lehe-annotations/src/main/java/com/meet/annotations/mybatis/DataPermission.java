package com.lehe.annotations.mybatis;


import java.lang.annotation.*;

/**
 * @author 2020
 * 2021-10-18 15:25
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DataPermission {
    String type() default "";

    DataColumn[] value() default {};

    MetaData[] metaData() default {};

    boolean ignore() default false;
}
