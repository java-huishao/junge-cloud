package com.zhny.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Data
@Component
@ConfigurationProperties(prefix = "security.oauth2")
public class OAuth2ClientProperties {
    private String client_id;
    private String client_secret;
    private String grant_type;
    private String scope;
    private String userAuthorizationUri;
    private String accessTokenUri;
    private String userInfoUri;
    private String checkTokenAccess;

    /**
     * -
     * 获取 oauth2 认证header
     * 传输为:Authorization:Basic Base64编码的(client_id:client_secret)
     *
     * @return Authorization:Basic bXljbG91ZDpteWNsb3Vk
     */
    public String getBasicHeader() {
        //Http Basic 验证
        String clientAndSecret = this.client_id + ":" + this.client_secret;
        //这里需要注意为 Basic 而非 Bearer
        clientAndSecret = "Basic " + Base64.getEncoder().encodeToString(clientAndSecret.getBytes());
        return clientAndSecret;
    }
}
