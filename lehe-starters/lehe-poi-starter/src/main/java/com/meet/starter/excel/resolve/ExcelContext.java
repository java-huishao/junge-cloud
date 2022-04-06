package com.lehe.starter.excel.resolve;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author yudong
 * 2019/6/15
 */
public class ExcelContext {
    private Workbook workbook;
    private Sheet sheet;
    private Row row;

    public Workbook getWorkbook() {
        return workbook;
    }

    void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public Sheet getSheet() {
        return sheet;
    }

    void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public Row getRow() {
        return row;
    }

    void setRow(Row row) {
        this.row = row;
    }
}
