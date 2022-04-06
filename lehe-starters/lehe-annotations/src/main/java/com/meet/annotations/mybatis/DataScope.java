package com.lehe.annotations.mybatis;

import java.lang.annotation.*;

/**
 * @author 2020
 * 2021-10-19 11:43
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DataScope {
    String type() default "";

    DataColumn[] value() default {};

    boolean ignore() default false;
}
