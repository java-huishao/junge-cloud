package com.lehe.starter.mybatisplus.annotations;

import com.lehe.starter.mybatisplus.DiBootconfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author 2020
 * @date 2022-03-09 11:22
 */
@Target({java.lang.annotation.ElementType.TYPE})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
@Import({DiBootconfig.class})
public @interface EnableDiboot {
}
