package com.lehe.starter.excel.annos;

import java.lang.annotation.*;

/**
 * @author yudong
 * 2019/6/9
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumnDecimal {

    /**
     * 浮点数格式
     */
    String decimalFormat();
}
