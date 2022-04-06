package com.zhny.starter.log.annotation;


import com.zhny.starter.log.enums.ActionEnum;
import com.zhny.starter.log.enums.ModelType;

import java.lang.annotation.*;

/**
 * @author houqj
 * 2019-09-30 10:26
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAction {

    /**
     * 模块名称
     */
    String appName() default "";

    /**
     * 子模块名称
     */
    ModelType model() default ModelType.NULL;

    /**
     * 操作类型
     */
    ActionEnum action() default ActionEnum.NULL;

    /**
     * 操作对象
     */
    String targetObject() default "";

    /**
     * 操作详情
     */
    String detail() default "";

    /**
     * 默认为false
     * false 注解在Controller,需要自己重写操作日志逻辑,返回Content
     * true 注解在操作记录实现类,针对已经写过操作记录的方法,直接修改操作记录返回值为Content类型,切面直接获取返回值。
     */
    boolean isReturn() default false;

    enum ActionType {LOGIN, AUDIT, ADD, UPDATE, DELETE, DISABLEACCOUNT}

}
