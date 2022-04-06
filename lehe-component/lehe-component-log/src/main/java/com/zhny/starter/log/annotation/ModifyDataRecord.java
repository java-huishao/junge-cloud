package com.zhny.starter.log.annotation;

import java.lang.annotation.*;

/**
 * @author houqj
 * 2021-04-08 16:22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModifyDataRecord {
    //更新数据id
    String id() default "";

    //更新数据名称
    String name() default "";

    //业务模块名称
    String module() default "";
}
