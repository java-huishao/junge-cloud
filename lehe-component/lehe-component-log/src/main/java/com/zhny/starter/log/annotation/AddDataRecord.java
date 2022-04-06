package com.zhny.starter.log.annotation;

import java.lang.annotation.*;

/**
 * @author houqj
 * 2021-04-08 16:22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AddDataRecord {
    //业务id
    String id() default "";

    //操作名称
    String actionName() default "";

    //业务模块code
    String module() default "";

    //业务模块名称
    String moduleName() default "";
}
