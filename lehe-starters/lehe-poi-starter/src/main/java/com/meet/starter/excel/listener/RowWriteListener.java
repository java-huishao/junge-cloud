package com.lehe.starter.excel.listener;

import com.lehe.starter.excel.resolve.ExcelContext;

/**
 * @author yudong
 * 2019/6/9
 */
public interface RowWriteListener<T> {

    /**
     * 标题行写入前回调方法
     *
     * @param context
     */
    default void headerBeforeWriteAction(ExcelContext context) {

    }

    /**
     * 标题行写入后回调方法
     *
     * @param context
     */
    default void headerAfterWriteAction(ExcelContext context) {

    }

    /**
     * 内容行写入前回调方法
     *
     * @param data    待写入的data
     * @param context
     */
    default void contentBeforeWriteAction(T data, ExcelContext context) {

    }

    /**
     * 内容行写入后回调方法
     *
     * @param data    已写入的data
     * @param context
     */
    default void contentAfterWriteAction(T data, ExcelContext context) {

    }


    /**
     * 尾部行写入前回调方法
     *
     * @param reverseRowIndex 倒数行下标
     * @param context
     */
    default void footerBeforeWriteAction(int reverseRowIndex, ExcelContext context) {

    }

    /**
     * 尾部行写入后回调方法
     *
     * @param reverseRowIndex 倒数行下标
     * @param context
     */
    default void footerAfterWriteAction(int reverseRowIndex, ExcelContext context) {

    }
}
