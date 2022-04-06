package com.zhny.starter.web.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author houqj
 * 2021-04-12 16:01
 */
@Slf4j
@UtilityClass
public class MetaDataUtil {

    /**
     * 获取返回结果对象注释
     *
     * @param o 对象1
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    public Map<String, Object> getResultMetaData(Object o) {
        Map<String, Object> map = new HashMap<>();
        if (null != o && !(o instanceof String)) {
            //获取对象的class
            Class<?> clazz = o.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
//                if (field.isAnnotationPresent(ApiModelProperty.class)) {
//                    ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
//                    String fieldName = field.getName();
//                    map.put(annotation.value(), fieldName);
//                }
            }
        }
        return map;
    }
}
