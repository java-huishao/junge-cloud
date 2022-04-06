package com.lehe.starter.excel.annos;

import com.lehe.starter.excel.enums.MoneyUnit;

import java.lang.annotation.*;

/**
 * @author yudong
 * 2019/6/9
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumnMoney {

    /**
     * 金额格式
     */
    String moneyFormat();

    /**
     * 金额单位
     */
    MoneyUnit moneyUnit();


}
