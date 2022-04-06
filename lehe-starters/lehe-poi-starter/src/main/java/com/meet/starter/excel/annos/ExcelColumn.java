package com.lehe.starter.excel.annos;

import java.lang.annotation.*;

/**
 * 标注bean属性对应的excel的列信息
 *
 * @author yudong
 * 2019/6/9
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    /**
     * 列名,传入多个表明是多级表头
     */
    String[] columnName();

    /**
     * 列顺序,越小越靠前
     */
    int columnOrder() default 999;

    /**
     * 列宽
     */
    int columnWidth() default 5000;

}
