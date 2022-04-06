package com.zhny.starter.common.result;

import lombok.Getter;

@Getter
public enum ResultEnum {

    //
    SUCCESS(200, "成功"),
    SAVE_SUCCESS(200, "保存成功"),
    SAVE_FAIL(200, "保存失败"),
    FAIL(500, "系统访问异常:"),
    BAD_PARAM(501, "参数异常"),
    AUTH_ERROR(401, "抱歉，您没有操作权限"),
    SERVER_ERROR(503, "系统服务异常"),
    BUSINESS_ERROR(504, "业务处理异常"),
    LOGIN_SESSION_MISS(505, "会话失效,请重新登录"),
    LOGIN_SUCCESS(506, "登录成功"),
    LOGIN_FAIL(507, "登录失败"),
    LOGINOUT_FAIL(508, "注销失败"),
    LOGIN_USER_ERR(509, "用户名或密码错误"),
    LOGIN_USER_locked(510, "用户帐号被锁定"),
    LOGIN_USER_disabled(511, "用户已禁用"),
    LOGIN_USER_credentialsexpired(512, "用户凭据已过期"),
    LOGIN_USER_expired(513, "用户帐户已过期"),
    LOGOUT_FAIL(514, "注销失败"),
    TOKEN_VALID_ERROR(515, "令牌校验失败"),
    TOKEN_MISS(516, "令牌缺失"),
    DATA_MISS(517, "数据为空"),
    PARAM_MISS(517, "缺少参数"),
    SYSTEM_ERROR(518, "系统异常"),
    SYSTEM_BUSY(519, "系统繁忙,请稍候再试"),
    SYSTEM_NO_PERMISSION(520, "无权限"),
    GATEWAY_NOT_FOUND_SERVICE(521, "服务未找到"),
    GATEWAY_ERROR(522, "网关异常"),
    GATEWAY_CONNECT_TIME_OUT(523, "网关超时"),
    ARGUMENT_NOT_VALID(524, "请求参数校验不通过"),
    UPLOAD_FILE_SIZE_LIMIT(525, "上传文件大小超过限制"),
    Tenant_account_exists(526, "注册失败，账户已存在！"),
    Admin_account_exists(527, "新增失败，账户已存在！"),
    Idempotency_version_valid_error(531, "签名验证失败，请重新获取签名！"),
    Idempotency_version_dont_repeat_request(532, "请勿重复请求！"),
    BAD_SQL(550, "sql错误,请检查sql字段语法"),
    MYBATIS_ERROR(551, "mybatis异常:"),
    Excel_type_error(700, "文件类型错误,请使用规定模板文件上传,请勿随意修改文件后缀！"),
    HTTP_CLIENT_ERROR(526, "服务调用异常"),
    HTTP_STATUS_ERROR(527, "服务调用异常,请稍后重试"),
    FEIGN_ERROR(528, "feign调用异常"),
    ZUUL_STATUS_ERROR(529, "服务调用太频繁，请稍后重试"),
    VALID_CODE_ERROR(530, "验证码输入错误"),
    FEIGN_CONTENT_EMPTY(531, "feign 调用数据返回为空！"),
    BUSINESS_TIME_OUT(580, "业务处理超时");

    private final Integer code;
    private final String desc;

    ResultEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

}
