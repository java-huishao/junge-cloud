package com.lehe.starter.excel.resolve;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author yudong
 * 2019/6/9
 */
public interface ExcelWriterService {

    /**
     * 获取workbook
     *
     * @return
     */
    Workbook getWorkBook();

    /**
     * 获取字节数组
     *
     * @return
     */
    byte[] getBytes();
}
