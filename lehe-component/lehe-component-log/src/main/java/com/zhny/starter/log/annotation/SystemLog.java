package com.zhny.starter.log.annotation;


import com.zhny.starter.log.enums.ModelType;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    /**
     * 子模块名称
     */
    ModelType model() default ModelType.NULL;

    /**
     * 日志代理类
     */
    Class logClass() default void.class;

}
