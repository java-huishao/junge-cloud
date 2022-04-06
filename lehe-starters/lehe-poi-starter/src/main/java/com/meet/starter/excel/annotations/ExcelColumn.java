package com.lehe.starter.excel.annotations;

import java.lang.annotation.*;

/**
 * 自定义实体类所需要的bean(Excel属性标题、位置等)
 *
 * @author Lynch
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
    /**
     * Excel标题
     *
     * @return Result 返回结果
     * @author Lynch
     */
    String value() default "";

    /**
     * Excel从左往右排列位置
     *
     * @return Result 返回结果
     * @author Lynch
     */
    int col() default 0;

    /**
     * 此列是否显示
     *
     * @return
     */
    boolean showColumn() default true;
}
