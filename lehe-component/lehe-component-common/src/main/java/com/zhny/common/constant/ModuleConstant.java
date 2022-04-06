package com.zhny.common.constant;

/**
 * @author houqj
 * 2021-04-02 10:52
 */
public interface ModuleConstant {
    String lehe = "lehe";
    String clues = "clues";
    String customer = "customer";
    String contacts = "contacts";
    String business = "business";
    String contract = "contract";
    String receivables = "receivables";
    String invoice = "invoice";
    String product = "product";
    String account = "account";
    String communicate = "communicate";
    String daily = "daily";

    String cluesFileName = "线索数据.xlsx";
    String customerFileName = "客户数据.xlsx";
    String contactsFileName = "联系人数据.xlsx";
    String businessFileName = "商机数据.xlsx";
    String receivablesFileName = "回款数据.xlsx";
    String invoiceFileName = "发票数据.xlsx";
    String contractFileName = "合同数据.xlsx";
    String productFileName = "产品数据.xlsx";
    String accountFileName = "用户数据.xlsx";

    static String getFileName(String module) {
        switch (module) {
            case customer:
                return customerFileName;
            case ModuleConstant.contacts:
                return contactsFileName;
            case ModuleConstant.business:
                return businessFileName;
            case ModuleConstant.contract:
                return contractFileName;
            case ModuleConstant.receivables:
                return receivablesFileName;
            case ModuleConstant.invoice:
                return invoiceFileName;
            case ModuleConstant.product:
                return productFileName;
            case ModuleConstant.account:
                return accountFileName;
        }
        return null;
    }
}
