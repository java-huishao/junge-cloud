package com.zhny.starter.common.utils;

import lombok.experimental.UtilityClass;

import java.util.*;

/**
 * @author houqj
 * 2021-04-09 17:52
 */
@UtilityClass
public class FSDateUtils {
    public static Random random = new Random();

    /**
     * @Description: 获取本年12个月的月份
     * @Param: []
     * @Return: java.util.List<java.lang.String>
     * @Author: houqj
     * : 2021/4/15 14:08
     **/
    public List<String> getMonthList() {
        List<String> monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add(cn.hutool.core.date.DateUtil.year(cn.hutool.core.date.DateUtil.date()) + "-" + String.format("%02d", i));
        }
        return monthList;
    }

    public Date getDayBefore(int before) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, before);
        return calendar1.getTime();
    }

    public static void main(String[] args) {
        getMonthList().forEach(e -> System.out.println(e));
////        gettargetAmount().forEach(e-> System.out.println(e));
////        getactualAmount().forEach(e-> System.out.println(e));
//        Integer dateType=3;
//        System.out.println(-dateType);
    }
}
