package com.lehe.starter.excel.listener;

import com.lehe.starter.excel.resolve.ExcelContext;

/**
 * @author yudong
 * 2019/6/9
 */
@FunctionalInterface
public interface RowReadListener<T> {

    /**
     * 行读取回调方法
     *
     * @param bean    行信息填充的对象
     * @param context
     * @return 返回false将会过滤掉此填充的对象
     */
    boolean accept(T bean, ExcelContext context);

}
