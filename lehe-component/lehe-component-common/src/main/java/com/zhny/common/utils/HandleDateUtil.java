package com.zhny.common.utils;

import cn.hutool.core.date.DateUtil;
import lombok.experimental.UtilityClass;

/**
 * @author houqj
 * 2021-03-28 9:34
 */
@UtilityClass
public class HandleDateUtil {

    private static final String today = "today";
    private static final String yesterday = "yesterday";
    private static final String currentWeek = "currentWeek";
    private static final String lastWeek = "lastWeek";
    private static final String recent30 = "recent30";
    private static final String recent60 = "recent60";
    private static final String currentMonth = "currentMonth";
    private static final String lastMonth = "lastMonth";
    private static final String currentQuarter = "currentQuarter";
    private static final String lastQuarter = "lastQuarter";
    private static final String currentYear = "currentYear";
    private static final String lastYear = "lastYear";
    private static final String customer = "customer";

    public String getStartDate(String dateType, String date) {
        String startDate = "";
        switch (dateType) {
            case today:
                startDate = DateUtil.today();
                break;
            case yesterday:
                startDate = DateUtil.yesterday().toDateStr();
                break;
            case currentWeek:
                startDate = DateUtil.beginOfWeek(DateUtil.date()).toDateStr();
                break;
            case lastWeek:
                startDate = DateUtil.beginOfWeek(DateUtil.lastWeek()).toDateStr();
                break;
            case recent30:
                startDate = DateUtil.offsetDay(DateUtil.date(), -30).toDateStr();
                break;
            case recent60:
                startDate = DateUtil.offsetDay(DateUtil.date(), -60).toDateStr();
                break;
            case currentMonth:
                startDate = DateUtil.beginOfMonth(DateUtil.date()).toDateStr();
                break;
            case lastMonth:
                startDate = DateUtil.beginOfMonth(DateUtil.lastMonth()).toDateStr();
                break;
            case currentQuarter:
                startDate = DateUtil.beginOfQuarter(DateUtil.date()).toDateStr();
                break;
            case lastQuarter:
                startDate = DateUtil.beginOfQuarter(DateUtil.offsetDay(DateUtil.beginOfQuarter(DateUtil.date()), -1)).toDateStr();
                break;
            case currentYear:
                startDate = DateUtil.beginOfYear(DateUtil.date()).toDateStr();
                break;
            case lastYear:
                startDate = DateUtil.beginOfYear(DateUtil.offsetDay(DateUtil.beginOfYear(DateUtil.date()), -1)).toDateStr();
                break;
            case customer:
                startDate = date;
                break;
            default:
                startDate = DateUtil.today();
        }

        return startDate;
    }

    public String getEndDate(String dateType, String date) {
        String endDate = "";
        switch (dateType) {
            case today:
                endDate = DateUtil.tomorrow().toDateStr();
                break;
            case yesterday:
                endDate = DateUtil.today();
                break;
            case currentWeek:
                endDate = DateUtil.offsetDay(DateUtil.endOfWeek(DateUtil.date()), 1).toDateStr();
                break;
            case lastWeek:
                endDate = DateUtil.offsetDay(DateUtil.beginOfWeek(DateUtil.lastWeek()), 1).toDateStr();
                break;
            case recent30:
                endDate = DateUtil.tomorrow().toDateStr();
                break;
            case recent60:
                endDate = DateUtil.tomorrow().toDateStr();
                break;
            case currentMonth:
                endDate = DateUtil.offsetDay(DateUtil.endOfMonth(DateUtil.date()), 1).toDateStr();
                break;
            case lastMonth:
                endDate = DateUtil.offsetDay(DateUtil.endOfMonth(DateUtil.lastMonth()), 1).toDateStr();
                break;
            case currentQuarter:
                endDate = DateUtil.offsetDay(DateUtil.endOfQuarter(DateUtil.date()), 1).toDateStr();
                break;
            case lastQuarter:
                endDate = DateUtil.offsetDay(DateUtil.endOfQuarter(DateUtil.offsetDay(DateUtil.beginOfQuarter(DateUtil.date()), -1)), 1).toDateStr();
                break;
            case currentYear:
                endDate = DateUtil.offsetDay(DateUtil.endOfYear(DateUtil.date()), 1).toDateStr();
                break;
            case lastYear:
                endDate = DateUtil.offsetDay(DateUtil.endOfYear(DateUtil.offsetDay(DateUtil.beginOfYear(DateUtil.date()), -1)), 1).toDateStr();
                break;
            case customer:
                endDate = DateUtil.offsetDay(DateUtil.parseDate(date), 1).toDateStr();
                break;
            default:
                endDate = DateUtil.tomorrow().toDateStr();
        }
        return endDate;
    }

    public static void main(String[] args) {
        System.out.println("今天====>" + DateUtil.today());
        System.out.println("昨天====>" + DateUtil.yesterday().toDateStr());
        System.out.println("本周一====>" + DateUtil.beginOfWeek(DateUtil.date()).toDateStr());
        System.out.println("本周日====>" + DateUtil.endOfWeek(DateUtil.date()).toDateStr());
        System.out.println("上周一====>" + DateUtil.beginOfWeek(DateUtil.lastWeek()).toDateStr());
        System.out.println("上周日====>" + DateUtil.endOfWeek(DateUtil.lastWeek()).toDateStr());
        System.out.println("最近30天的第一天====>" + DateUtil.offsetDay(DateUtil.date(), -30).toDateStr());
        System.out.println("最近60天的第一天====>" + DateUtil.offsetDay(DateUtil.date(), -60).toDateStr());
        System.out.println("当前月第一天====>" + DateUtil.beginOfMonth(DateUtil.date()).toDateStr());
        System.out.println("当前月最后一天====>" + DateUtil.endOfMonth(DateUtil.date()).toDateStr());
        System.out.println("上个月第一天====>" + DateUtil.beginOfMonth(DateUtil.lastMonth()).toDateStr());
        System.out.println("上个月最后一天====>" + DateUtil.endOfMonth(DateUtil.lastMonth()).toDateStr());
        System.out.println("当前季度第一天====>" + DateUtil.beginOfQuarter(DateUtil.date()).toDateStr());
        System.out.println("当前季度最后一天====>" + DateUtil.endOfQuarter(DateUtil.date()).toDateStr());
        System.out.println("上季度第一天====>" + DateUtil.beginOfQuarter(DateUtil.offsetDay(DateUtil.beginOfQuarter(DateUtil.date()), -1)).toDateStr());
        System.out.println("上季度最后一天====>" + DateUtil.endOfQuarter(DateUtil.offsetDay(DateUtil.beginOfQuarter(DateUtil.date()), -1)).toDateStr());
        System.out.println("今年第一天====>" + DateUtil.beginOfYear(DateUtil.date()).toDateStr());
        System.out.println("今年最后一天====>" + DateUtil.endOfYear(DateUtil.date()).toDateStr());
        System.out.println("去年第一天====>" + DateUtil.beginOfYear(DateUtil.offsetDay(DateUtil.beginOfYear(DateUtil.date()), -1)).toDateStr());
        System.out.println("去年最后一天====>" + DateUtil.endOfYear(DateUtil.offsetDay(DateUtil.beginOfYear(DateUtil.date()), -1)).toDateStr());
        System.out.println("格式化====>" + DateUtil.offsetDay(DateUtil.parseDate("2021-03-29"), 1).toDateStr());
    }
}
