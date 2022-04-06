package com.zhny.starter.common.annotations;

import java.lang.annotation.*;

/**
 * @author houqj
 * 2019-09-30 10:26
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    String id() default "";

    String userName() default "";

}
