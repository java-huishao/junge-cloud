package com.zhny.strategy.enums;

import lombok.Getter;

/**
 * @author houqj
 * 2021-02-17 11:59
 * 枚举参数
 * 前端只用传code
 */
@Getter
public enum FormTypeEnum {

    PAY_ALIBABA("alipay", "阿里云支付"),
    PAY_WECHAT("weichatpay", "微信支付");


    private final String code;
    private final String souce;

    FormTypeEnum(String code, String souce) {
        this.code = code;
        this.souce = souce;
    }
}
