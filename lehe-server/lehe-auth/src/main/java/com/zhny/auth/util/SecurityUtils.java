package com.zhny.auth.util;


import cn.hutool.core.util.StrUtil;
import com.zhny.auth.service.SysUser;
import com.lehe.common.model.AuthAccount;
import com.zhny.starter.common.constant.SecurityConstants;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 安全工具类
 *
 * @author L.cm
 */
@UtilityClass
public class SecurityUtils {
    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     *
     * @param authentication
     * @return Result 返回结果 SysUser
     * <p>
     * 获取当前用户的全部信息 EnableResourceServer true
     * 获取当前用户的用户名 EnableResourceServer false
     */
    public SysUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof AuthAccount) {
            return (SysUser) principal;
        }
        return null;
    }

    /**
     * 获取当前用名
     *
     * @return Result 返回结果 String
     */
    public String getUsername() {
        Object principal = getAuthentication().getPrincipal();
        if (principal instanceof String) {
            return principal.toString();
        }
        return null;
    }


    public String getClientId() {
        Authentication authentication = getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
            return auth2Authentication.getOAuth2Request().getClientId();
        }
        return null;
    }

    /**
     * 获取用户
     */
    public SysUser getUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }

    /**
     * 获取用户角色信息
     *
     * @return Result 返回结果 角色集合
     */
    public List<String> getRoles() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = new ArrayList<>();
        authorities.stream()
                .filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstants.ROLE))
                .forEach(granted -> {
                    roles.add(granted.toString());
                });
        return roles;
    }

}
