package com.zhny.strategy.annonation;

import com.zhny.strategy.enums.FormTypeEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 策略模式注解
 *
 * @author pangu
 * 2020-10-5
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface HandlerType {

    FormTypeEnum formTypeEnum();
}
