package com.starter.excel;

import com.lehe.starter.excel.enums.ExcelType;
import com.lehe.starter.excel.listener.RowWriteListener;
import com.lehe.starter.excel.model.FooterColumn;
import com.lehe.starter.excel.model.FooterRow;
import com.lehe.starter.excel.model.Style;
import com.lehe.starter.excel.resolve.*;
import com.lehe.starter.excel.utils.StyleUtils;
import com.starter.excel.example.MultiHeadExampleBean;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yudong
 * 2019/6/9
 */
public class MultiHeaderExampleTest {

    @Test
    public void testWrite() throws Exception {

        List<MultiHeadExampleBean> list = IntStream.rangeClosed(1, 3).mapToObj(this::generateBean).collect(Collectors.toList());

        ExcelWriter<MultiHeadExampleBean> excelWriter = new ExcelWriter<>(ExcelType.XLS);
        SheetInfo<MultiHeadExampleBean> sheetInfo = new SheetInfo<>(list, MultiHeadExampleBean.class, "交易信息", 2);

        List<FooterColumn> footerColumnList = new ArrayList<>(1);
        footerColumnList.add(new FooterColumn("合计", 1));
        footerColumnList.add(new FooterColumn("SUM", true));
        footerColumnList.add(new FooterColumn("SUM", true));
        FooterRow footerRow = new FooterRow(footerColumnList);

        sheetInfo.setFooterRowList(Collections.singletonList(footerRow));

        excelWriter.addSheetInfo(sheetInfo);
        excelWriter.setRowWriteListener(new RowWriteListener<MultiHeadExampleBean>() {
            @Override
            public void headerAfterWriteAction(ExcelContext context) {
                int rowIndex = context.getRow().getRowNum();
                if (rowIndex == 1) {
                    Style style = Style.builder().foregroundColor(IndexedColors.BLUE_GREY.index).build();
                    CellStyle cellStyle = StyleUtils.getCommonCellStyle(context.getWorkbook(), style);
                    context.getRow().getCell(2).setCellStyle(cellStyle);
                }
            }

            @Override
            public void contentAfterWriteAction(MultiHeadExampleBean data, ExcelContext context) {
                System.out.println(context.getRow().getRowNum());
            }

            @Override
            public void footerAfterWriteAction(int reverseRowIndex, ExcelContext context) {
                if (reverseRowIndex == 0) {
                    Style style = Style.builder().foregroundColor(IndexedColors.RED.index).decimalPattern("0.00").build();
                    CellStyle cellStyle = StyleUtils.getCommonCellStyle(context.getWorkbook(), style);
                    context.getRow().getCell(2).setCellStyle(cellStyle);
                    Style style1 = Style.builder().foregroundColor(IndexedColors.GREEN.index).decimalPattern("0.0000").build();
                    CellStyle cellStyle1 = StyleUtils.getCommonCellStyle(context.getWorkbook(), style1);
                    context.getRow().getCell(3).setCellStyle(cellStyle1);
                }
            }
        });
        ExcelWriterService excelWriterService = excelWriter.addSheetFinish();

        Workbook workbook = excelWriterService.getWorkBook();
        File file = new File("D:\\multi-header-excel-test.xls");
        OutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();

    }

    @Test
    public void testRead() throws Exception {

        File file = new File("D:\\multi-header-excel-test.xls");
        InputStream inputStream = new FileInputStream(file);
        ExcelReader<MultiHeadExampleBean> excelReader = new ExcelReader<>(ExcelType.XLS, inputStream, MultiHeadExampleBean.class, 2, 1);
        excelReader.setRowReadListener((bean, context) -> {
            System.out.println(bean);
            return true;
        });
        excelReader.read();
        List<MultiHeadExampleBean> list = excelReader.getResultList();
        System.out.println(list);

    }

    private MultiHeadExampleBean generateBean(int i) {
        MultiHeadExampleBean bean = new MultiHeadExampleBean();
        bean.setIndex(i);
        bean.setOrderCode("TC000000" + i);
        bean.setOrderPrice(i * 1000L);
        bean.setOrderBase(i * 1000.2555);
        bean.setPayTime(DateUtils.addMonths(new Date(), -i * 2));
        bean.setCustomerName("yudong" + i + 2);
        bean.setCustomerPhone("13800138000" + i);
        bean.setProvince("Guangdong" + i);
        bean.setCity("Guangzhou" + i);
        bean.setVillage("Tianhe cun" + i);
        bean.setStreet("jichang road" + i);
        return bean;
    }
}
