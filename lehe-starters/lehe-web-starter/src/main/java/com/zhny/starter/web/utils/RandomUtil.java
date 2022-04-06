package com.zhny.starter.web.utils;

import java.util.Random;

/**
 * @Description 生成随机数工具类
 */
public class RandomUtil {

    /**
     * 生成随机数长度
     *
     * @param size 页码
     * @return Result 返回结果
     */
    public static String getRandom(int size) {
        StringBuffer str = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
