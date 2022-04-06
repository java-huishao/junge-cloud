package com.zhny.starter.common.utils;

import com.zhny.starter.common.oauth2.OAuth2AccessToken;
import com.zhny.starter.common.result.Result;
import com.zhny.starter.common.result.ResultEnum;
import org.apache.commons.lang3.StringUtils;

public class GrantTokenUtil {
    private static final String disabled = "User is disabled";
    private static final String expired = "User account has expired";
    private static final String locked = "User account is locked";
    private static final String badcredentials = "Bad credentials";
    private static final String credentialsexpired = "User credentials have expired";

    private static final String BAD = "User is disabled";

    public static Result<Object> grantUserToken(OAuth2AccessToken oAuth2AccessToken) {
        if (StringUtils.isNotBlank(oAuth2AccessToken.getAccess_token())) {
            return Result.success(oAuth2AccessToken.getAccess_token());
        } else {
            if (disabled.equals(oAuth2AccessToken.getMessage())) {
                return Result.fail(ResultEnum.LOGIN_USER_disabled);
            } else if (expired.equals(oAuth2AccessToken.getMessage())) {
                return Result.fail(ResultEnum.LOGIN_USER_expired);
            } else if (locked.equals(oAuth2AccessToken.getMessage())) {
                return Result.fail(ResultEnum.LOGIN_USER_locked);
            } else if (credentialsexpired.equals(oAuth2AccessToken.getMessage())) {
                return Result.fail(ResultEnum.LOGIN_USER_credentialsexpired);
            } else if (badcredentials.equals(oAuth2AccessToken.getMessage())) {
                return Result.fail(ResultEnum.LOGIN_USER_ERR);
            }
            return Result.fail(ResultEnum.LOGIN_USER_ERR);
        }
    }
}
