package com.zhny.starter.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author houqj
 * 2019-07-24 11:18
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonDes {
    /**
     * 前缀长度，默认为3
     */
    int prefixLength() default 3;

    /**
     * 后缀长度，默认为4
     */
    int suffixLength() default 4;

    /**
     * 星号个数，默认为6
     */
    int asteriskNum() default 6;
}
