package com.zhny.starter.web.utils;

import cn.hutool.core.date.DateUtil;
import com.zhny.starter.common.annotations.FieldMeta;
import com.zhny.starter.common.utils.ActionContentUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author houqj
 * 2021-04-07 16:58
 * https://blog.csdn.net/itbiggod/article/details/106033696
 */
public class CompareObjectUtils {

    /**
     * 获取两个对象同名属性内容不相同的列表
     *
     * @param class1 对象1
     * @param class2 对象2
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    public static List<String> compareTwoClass(Object class1, Object class2) throws IllegalAccessException {
        List<String> list = new ArrayList<String>();
        //获取对象的class
        Class<?> clazz1 = class1.getClass();
        Class<?> clazz2 = class2.getClass();
        //获取对象的属性列表
        Field[] field1 = clazz1.getDeclaredFields();
        Field[] field2 = clazz2.getDeclaredFields();
        //遍历属性列表field1
        for (int i = 0; i < field1.length; i++) {
            if (field1[i].isAnnotationPresent(FieldMeta.class)) {
                //遍历属性列表field2
                for (int j = 0; j < field2.length; j++) {
                    //如果field1[i]属性名与field2[j]属性名内容相同
                    if (field1[i].getName().equals(field2[j].getName())) {
                        field1[i].setAccessible(true);
                        field2[j].setAccessible(true);
                        //如果field1[i]属性值与field2[j]属性值内容不相同
                        if (!compareTwo(field1[i].get(class1), field2[j].get(class2)) && field1[i].isAnnotationPresent(FieldMeta.class) && field2[j].isAnnotationPresent(FieldMeta.class)) {
                            FieldMeta metaAnnotation = field1[i].getAnnotation(FieldMeta.class);
                            Map<String, Object> map2 = new HashMap<String, Object>();
                            map2.put("name", metaAnnotation.name());
                            map2.put("old", field1[i].get(class1) == null ? "" : field1[i].get(class1));
                            map2.put("new", field2[j].get(class2));
                            String actionContent = ActionContentUtil.getActionContent(metaAnnotation.name(), field1[i].get(class1) == null ? "" : field1[i].get(class1), field2[j].get(class2));
                            //解决时间格式化问题-bean上加了@DateTimeFormat(pattern="yyyy-MM-dd")
                            if (field1[i].isAnnotationPresent(DateTimeFormat.class) && field2[j].isAnnotationPresent(DateTimeFormat.class)) {
                                String old = DateUtil.format((Date) field1[i].get(class1), field1[i].getAnnotation(DateTimeFormat.class).pattern());
                                map2.put("old", old == null ? "" : old);
                                map2.put("new", DateUtil.format((Date) field2[j].get(class2), field2[j].getAnnotation(DateTimeFormat.class).pattern()));
                                actionContent = ActionContentUtil.getActionContent(metaAnnotation.name(), old == null ? "" : old, DateUtil.format((Date) field2[j].get(class2), field2[j].getAnnotation(DateTimeFormat.class).pattern()));
                            }
                            //解决数据字典text/value转换问题-bean上加了@Dict(dicCode = "groupField",isCommon = false)
//                            if (field1[i].isAnnotationPresent(Dict.class) && field2[j].isAnnotationPresent(Dict.class)) {
//                                OAuth2AccessToken user = TenantContextHolder.getUser();
//                                boolean isCommon = field1[i].getAnnotation(Dict.class).isCommon();
//                                if (!isCommon) {
//                                    map2.put("old", compareObjectUtils.sysDictMapper.queryDictTextByKey(field1[i].getAnnotation(Dict.class).dicCode(), (String) field1[i].get(class1), null));
//                                    map2.put("new", compareObjectUtils.sysDictMapper.queryDictTextByKey(field2[j].getAnnotation(Dict.class).dicCode(), (String) field2[j].get(class2), null));
//                                } else {
//                                    map2.put("old", compareObjectUtils.sysDictMapper.queryDictTextByKey(field1[i].getAnnotation(Dict.class).dicCode(), (String) field1[i].get(class1), user.getTenantId()));
//                                    map2.put("new", compareObjectUtils.sysDictMapper.queryDictTextByKey(field2[j].getAnnotation(Dict.class).dicCode(), (String) field2[j].get(class2), user.getTenantId()));
//                                }
//                            }
                            list.add(actionContent);
                        }
                        break;
                    }
                }
            }
        }
        return list;
    }

    public static boolean compareTwo(Object object1, Object object2) {

        if (object1 == null && object2 == null) {
            return true;
        }
        // 因源数据是没有进行赋值，是null值，改为""。
        //if (object1 == "" && object2 == null) {
        //    return true;
        //}
        //if (object1 == null && object2 == "") {
        //    return true;
        // }
        if (object1 == null && object2 != null) {
            return false;
        }
        return object1.equals(object2);
    }

}
