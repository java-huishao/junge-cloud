package com.zhny.starter.log.annotation;

import java.lang.annotation.*;

/**
 * @author houqj
 * 2021-04-08 16:22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateDataRecord {
    //id
    String id() default "";

    //操作名称
    String actionName() default "";

    //模块名称
    String module() default "";

    //操作类型
    int operateType();
}
