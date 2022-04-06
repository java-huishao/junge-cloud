package com.zhny.starter.common.annotations;

/**
 * @author houqj
 * 2021-04-07 16:57
 */

import java.lang.annotation.*;

//原文链接：https://blog.csdn.net/itbiggod/article/details/106033696
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.FIELD, ElementType.METHOD})//定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented
public @interface FieldMeta {
    String name() default "";

    String description() default "";
}
