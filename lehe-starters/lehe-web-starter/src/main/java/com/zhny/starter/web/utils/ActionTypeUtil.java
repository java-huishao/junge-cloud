package com.zhny.starter.web.utils;

import lombok.experimental.UtilityClass;

/**
 * @author houqj
 * 2021-01-17 15:19
 */
@UtilityClass
public class ActionTypeUtil {
    public static String getActionType(String actionType) {
        switch (actionType) {
            case "LOGIN":
                return "禁用账号";
            case "AUDIT":
                return "审核";
            case "ADD":
                return "添加";
            case "UPDATE":
                return "编辑";
            case "DELETE":
                return "删除";
            case "DISABLEACCOUNT":
                return "禁用账号";
        }
        return "";
    }
}
