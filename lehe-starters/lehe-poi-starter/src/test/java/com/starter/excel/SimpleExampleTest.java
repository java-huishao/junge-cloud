package com.starter.excel;

import com.lehe.starter.excel.enums.ExcelType;
import com.lehe.starter.excel.listener.RowWriteListener;
import com.lehe.starter.excel.model.Style;
import com.lehe.starter.excel.resolve.*;
import com.lehe.starter.excel.utils.StyleUtils;
import com.starter.excel.example.SimpleExampleBean;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yudong
 * 2019/6/9
 */
public class SimpleExampleTest {

    @Test
    public void testWrite() throws Exception {

        List<SimpleExampleBean> list = IntStream.rangeClosed(1, 3).mapToObj(this::generateBean).collect(Collectors.toList());

        ExcelWriter<SimpleExampleBean> excelWriter = new ExcelWriter<>(ExcelType.XLS);
        SheetInfo<SimpleExampleBean> sheetInfo = new SheetInfo<>(list, SimpleExampleBean.class, "交易信息", 1);
        excelWriter.addSheetInfo(sheetInfo);
        excelWriter.setRowWriteListener(new RowWriteListener<SimpleExampleBean>() {
            @Override
            public void headerAfterWriteAction(ExcelContext context) {
                int rowIndex = context.getRow().getRowNum();
                if (rowIndex == 0) {
                    Style style = Style.builder().foregroundColor(IndexedColors.BLUE_GREY.index).build();
                    CellStyle cellStyle = StyleUtils.getCommonCellStyle(context.getWorkbook(), style);
                    context.getRow().getCell(2).setCellStyle(cellStyle);
                }
            }

            @Override
            public void contentAfterWriteAction(SimpleExampleBean data, ExcelContext context) {
                System.out.println(context.getRow().getRowNum());
            }
        });
        ExcelWriterService excelWriterService = excelWriter.addSheetFinish();

        Workbook workbook = excelWriterService.getWorkBook();
        File file = new File("D:\\simple-excel-test.xls");
        OutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();

    }

    @Test
    public void testRead() throws Exception {

        File file = new File("D:\\simple-excel-test.xls");
        InputStream inputStream = new FileInputStream(file);
        ExcelReader<SimpleExampleBean> excelReader = new ExcelReader<>(ExcelType.XLS, inputStream, SimpleExampleBean.class, 1, 0);
        excelReader.setRowReadListener((bean, context) -> {
            System.out.println(bean);
            return true;
        });
        excelReader.read();
        List<SimpleExampleBean> list = excelReader.getResultList();
        System.out.println(list);

    }

    private SimpleExampleBean generateBean(int i) {
        SimpleExampleBean bean = new SimpleExampleBean();
        bean.setIndex(i);
        bean.setOrderCode("KC000000" + i);
        bean.setOrderPrice(i * 1000L);
        bean.setOrderBase(i * 1000.2555);
        bean.setPayTime(DateUtils.addMonths(new Date(), -i * 2));
        bean.setConfirmTime(new Timestamp(DateUtils.addMonths(new Date(), -i * 2).getTime()));
        bean.setCustomerName("yudong" + i * 2);
        bean.setCustomerPhone("13800138000" + i);
        bean.setProvince("Guangdong" + i);
        bean.setCity("Guangzhou" + i);
        bean.setVillage("Tianhe cun" + i);
        bean.setStreet("jichang road" + i);
        return bean;
    }
}
