package com.zhny.common.enums;

import lombok.Getter;

/**
 * @author houqj
 * 2021-02-17 11:59
 * 审核状态枚举
 */
@Getter
public enum TestEnum {

    customer_all(0, "全部"),

    customer_me_create(1, "我创建的"),
    customer_underling_create(2, "下属创建的"),
    customer_wait_follow(3, "今日待跟进"),
    customer_contacted(4, "今日已联系的客户"),
    customer_followed(5, "联合跟进客户"),
    customer_unfollowed(6, "从未跟进客户"),

    three(7, "近3日"),
    seven(8, "近7日"),

    contacts_all(0, "全部"),
    contacts_me_create(1, "我创建的"),


    business_all(0, "全部"),
    business_me_create(1, "我创建的"),
    business_underling_create(2, "我下属的商机"),
    business_today_add(3, "今日新增"),
    business_wait_follow(4, "今日待跟进"),


    contract_all(0, "全部"),
    contract_me_create(1, "我创建的"),
    contract_underling_create(2, "我下属创建的"),


    receivables_all(0, "全部"),
    receivables_me_create(1, "我负责的"),
    receivables_underling_create(2, "我下属负责的"),


    invoice_all(0, "全部"),
    invoice_me_create(1, "我负责的"),
    invoice_underling_create(2, "我下属负责的"),
    invoice_wait_invoice(3, "待开具的发票"),

    product_all(0, "全部"),
    product_me_create(1, "我创建的"),
    product_underling_create(2, "我下属创建的"),
    product_on_shelf_up(3, "上架中"),
    product_ons_shelf_down(4, "已下架");


    private final Integer code;
    private final String name;

    TestEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
