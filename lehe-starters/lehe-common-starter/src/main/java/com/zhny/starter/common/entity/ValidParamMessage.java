package com.zhny.starter.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 2020
 * 2021-06-03 16:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidParamMessage {
    private String fieldName;
    private List<String> message;
}
