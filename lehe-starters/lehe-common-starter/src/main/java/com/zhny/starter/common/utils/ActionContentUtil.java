package com.zhny.starter.common.utils;

/**
 * @author houqj
 * 2021-04-07 17:08
 */
public class ActionContentUtil {

    public static final String fieldChange = "将 [%s] 由: [%s] 修改为: [%s] 。";

    public static String getActionContent(String name, Object original, Object current) {
        return String.format(fieldChange, name, current, original);
    }

    public static void main(String[] args) {
        String actionContent = getActionContent("客户名称", "张三", "李四");
        System.out.println(actionContent);
    }
}
