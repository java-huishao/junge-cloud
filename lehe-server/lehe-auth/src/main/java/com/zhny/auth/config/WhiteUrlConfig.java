package com.zhny.auth.config;

public class WhiteUrlConfig {
    //oauth2 server 放行白名单
    public static final String[] whiteUrlList = new String[]{"/oauth/remove_token", "/actuator/**", "/admin/testUser"};
}
