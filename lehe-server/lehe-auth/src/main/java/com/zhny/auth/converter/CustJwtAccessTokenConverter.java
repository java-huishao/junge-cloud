package com.zhny.auth.converter;

import cn.hutool.core.codec.Base64;
import com.zhny.auth.service.SysUser;
import com.zhny.starter.common.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author houqijun
 * com.lehe.admin.converter
 * @Description: 自定义转换令牌
 * <p>
 * 现在让我们设置一些基础设施，以便能够在访问令牌中添加一些自定义声明。框架提供的标准声明都很好，
 * 但大多数情况下我们需要在令牌中使用一些额外的信息来在客户端使用。 我们将定义一个TokenEnhancer来定制我们的Access Token与这些额外的声明。
 * 在下面的例子中，我们将添加一个额外的字段“组织”到我们的访问令牌 - 与此CustomTokenEnhancer：
 * <p>
 * 2018/7/2 14:53
 */
@Slf4j
public class CustJwtAccessTokenConverter extends JwtAccessTokenConverter {

    /**
     * refresh_token 不加密
     *
     * @param accessToken
     * @param authentication
     * @return Result 返回结果
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Object principal = authentication.getPrincipal();
        log.debug("oauth2token===>{}", authentication);
        log.debug("principal===>{}", principal);
        DefaultOAuth2AccessToken result = new DefaultOAuth2AccessToken(accessToken);
        Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());
        String tokenId = result.getValue();
        if (!info.containsKey(TOKEN_ID)) {
            info.put(TOKEN_ID, tokenId);
        }
        info.put("organization", authentication.getName() + RandomStringUtils.randomAlphabetic(4));
        SysUser sysUser = (SysUser) authentication.getUserAuthentication().getPrincipal();
        List<String> authorities = sysUser.getAuthorities().stream().map(f -> f.getAuthority()).collect(Collectors.toList());
        info.put("id", sysUser.getId());
        info.put("userType", sysUser.getUserType());
        info.put("account", sysUser.getUsername());
        info.put("name", Base64.encode(sysUser.getName()));
        info.put("license", SecurityConstants.MYCLOUD_LICENSE);
        info.put("roleName", Base64.encode(authorities.toString()));
        info.put("openTime", sysUser.getOpenTime());
        info.put("tenantId", sysUser.getTenantId());
        info.put("btns", sysUser.getBtns());
        log.debug("auth服务用户信息==>{}", info);
        result.setAdditionalInformation(info);
        result.setValue(encode(result, authentication));
        return result;
    }
}
