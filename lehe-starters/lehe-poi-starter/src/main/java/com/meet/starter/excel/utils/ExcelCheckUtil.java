package com.lehe.starter.excel.utils;

import org.apache.poi.poifs.filesystem.FileMagic;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author 2020
 * 2021-07-15 9:23
 * //    原文链接：https://blog.csdn.net/merryxuan/article/details/107560974
 */
public class ExcelCheckUtil {

    static final List<String> allowTypes = Arrays.asList("xls", "xlsx", "XLS", "XLSX");

    public static boolean isExcelFile(InputStream is) {
        boolean result = false;
        try {
            FileMagic fileMagic = FileMagic.valueOf(new BufferedInputStream(is));
            if (Objects.equals(fileMagic, FileMagic.OLE2)
                    || Objects.equals(fileMagic, FileMagic.OOXML)) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return result;
    }

    /**
     * @param originalFilename
     * @return
     */
    public static boolean validateSuffix(String originalFilename) {
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1); //截取点后面的字符串   lastIndexOf返回最后一次出现的位置
        return allowTypes.contains(suffix);
    }

    public static void main(String[] args) {
        boolean b = validateSuffix("测试");
        System.out.println(b);
    }
}
