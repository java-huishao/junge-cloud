package com.zhny.auth.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Map;

/**
 * @author houqj
 * 2021-01-26 11:35
 */
public class SmsTokenGranter extends AbstractTokenGranter {
    private static final String GRANT_TYPE = "sms_code";
    private final UserDetailsService userDetailsService;

    protected SmsTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, UserDetailsService userDetailsService) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = tokenRequest.getRequestParameters();
        UserDetails userDetails = checkPhoneSms(parameters);
        PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(userDetails);
        OAuth2Request oAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(oAuth2Request, authenticationToken);

    }

    /**
     * 自定义手机验证码校验
     */
    public UserDetails checkPhoneSms(Map<String, String> parameters) {
        String username = parameters.get("username");
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return userDetails;
    }
}
