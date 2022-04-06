package com.zhny.starter.common.entity;

import lombok.Data;

/**
 * @author 2020
 * 2021-05-17 10:19
 */
@Data
public class KV<T, V> {
    private T key;
    private V value;
}
