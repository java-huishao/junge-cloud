package com.zhny.starter.common.oauth2;

import lombok.Data;

import java.io.Serializable;

/**
 * 调用 oauth2 服务===>返回的json数据实体类
 */
@Data
public class OAuth2AccessToken implements Serializable {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;
    private String license;
    private String error;
    private String message;

    public OAuth2AccessToken() {
    }

    public OAuth2AccessToken(String access_token, String token_type, String refresh_token, long expires_in, String scope, String license, String error, String message) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
        this.expires_in = expires_in;
        this.scope = scope;
        this.license = license;
        this.error = error;
        this.message = message;
    }
}
