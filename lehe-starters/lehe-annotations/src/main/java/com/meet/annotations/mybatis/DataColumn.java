package com.lehe.annotations.mybatis;

import java.lang.annotation.*;

/**
 * @author 2020
 * 2021-10-19 11:42
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Repeatable(DataScope.class)
public @interface DataColumn {
    String alias() default "";

    String name();
}
