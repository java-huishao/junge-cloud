package com.lehe.starter.excel.annos;

import java.lang.annotation.*;

/**
 * @author yudong
 * 2019/6/9
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumnDate {
    /**
     * 日期类型的格式
     */
    String datePattern();
}
