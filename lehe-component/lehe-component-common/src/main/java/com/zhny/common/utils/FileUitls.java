package com.zhny.common.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

/**
 * @author Administrator
 * 2021-05-05 15:57
 */
@UtilityClass
public class FileUitls {

    /**
     * @Description: 将文件大小转化为M，或者G
     * @Param: [fileSize]
     * @Return: java.lang.String
     * @Author: Administrator
     * : 2021/5/5 15:58
     **/
    public static String getFileSize(BigDecimal fileSize) {
        String fileSizeString = "";
        //默认单位为kb
        if (fileSize.intValue() < 1024) {
            fileSizeString = fileSize + "B";
        } else if (fileSize.intValue() < 1048576) {
            fileSizeString = fileSize.divide(new BigDecimal(1024), 2, BigDecimal.ROUND_UP) + "KB";
        } else if (fileSize.intValue() < 1073741824) {
            fileSizeString = fileSize.divide(new BigDecimal(1048576), 2, BigDecimal.ROUND_UP) + "MB";
        } else {
            fileSizeString = fileSize.divide(new BigDecimal(1073741824), 2, BigDecimal.ROUND_UP) + "GB";
        }
        return fileSizeString;
    }
}
