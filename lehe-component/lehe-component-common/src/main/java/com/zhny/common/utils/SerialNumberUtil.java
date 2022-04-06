package com.zhny.common.utils;

import com.zhny.common.constant.CommonConstant;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 这个类演示了文档注释
 * 2021年10月30日10:36:54
 *
 * @author Ayan Amhed
 * @version 1.2
 */
public class SerialNumberUtil {

    private static final String QY_prefix = "QY";
    private static final String HT_prefix = "HT";
    private static final String FP_prefix = "QY";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(CommonConstant.yyyyMMddhhmmssSSS);

    public static String generateCompanyNumber() {
        return QY_prefix + sdf.format(new Date());
    }

    public static String generateContractNumber() {
        return HT_prefix + sdf.format(new Date());
    }

    public static String generateInvoiceNumber() {
        return FP_prefix + sdf.format(new Date());
    }
}
