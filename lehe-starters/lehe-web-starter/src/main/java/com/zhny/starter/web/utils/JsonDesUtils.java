package com.zhny.starter.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.zhny.starter.common.annotations.JsonDes;

import java.lang.reflect.Field;

/**
 * @author houqj
 * 2019-07-24 11:17
 */
public class JsonDesUtils {
    /**
     * 将日志数据对象转换成脱敏后的Json字符串
     *
     * @param logData 日志数据对象
     * @return Result 返回结果 日志 Json
     */
    public static String toLogString(Object logData) {
        return JSON.toJSONString(logData, (ValueFilter) (obj, name, value) -> {
            if (value instanceof String) {
                Class<?> clazz = obj.getClass();
                Field field = getDeclaredFiled(clazz, name);

                if (field != null) {
                    JsonDes jsonDes = field.getDeclaredAnnotation(JsonDes.class);
                    if (null != jsonDes) {
                        return desensitization((String) value, jsonDes.prefixLength(), jsonDes.suffixLength(), jsonDes.asteriskNum());
                    }
                }
            }
            return value;
        });
    }

    /**
     * 获取 Class 中的 Field
     *
     * @param clazz     类字节码对象
     * @param filedName 字段名称
     * @return Result 返回结果 字段字节码对象
     */
    private static Field getDeclaredFiled(Class<?> clazz, String filedName) {
        Field declaredField = null;
        while (clazz != null) {
            try {
                declaredField = clazz.getDeclaredField(filedName);
                if (declaredField != null || clazz.getSuperclass() == null) {
                    break;
                }
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return declaredField;
    }

    /**
     * 脱敏字符串
     *
     * @param value        原始字符串
     * @param prefixLength 前缀长度
     * @param suffixLength 后缀长度
     * @param asteriskNum  星号个数
     * @return Result 返回结果 脱敏后的字符串
     */
    private static String desensitization(String value, int prefixLength, int suffixLength, int asteriskNum) {
        if (value != null && value.length() >= Math.max(prefixLength, suffixLength)) {
            StringBuilder builder = new StringBuilder();
            builder.append(value, 0, prefixLength);
            for (int i = 0; i < asteriskNum; i++) {
                builder.append("*");
            }
            builder.append(value.substring(value.length() - suffixLength));
            return builder.toString();
        }
        return value;
    }

}
