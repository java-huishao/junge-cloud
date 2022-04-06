package com.zhny.auth.service;

import com.zhny.auth.feign.AdminClient;
import com.zhny.auth.feign.PortalClient;
import com.zhny.common.constant.LoginConstant;
import com.zhny.common.model.AuthAccount;
import com.zhny.starter.common.result.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户详细信息
 *
 * @author Houqijun
 */
@Slf4j
@Service
@AllArgsConstructor
public class MyUserDetailsServiceImpl implements UserDetailsService {
    private final AdminClient adminClient;
    private final PortalClient portalClient;

    private final CacheManager cacheManager;

    /**
     * 用户密码登录
     *
     * @param username 用户名
     * @return Result 返回结果
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("登录用户名====>{}", username);
        String[] split = username.split("-");
        if (split[0].equals(LoginConstant.ADMINISTRATOR)) {
            Result<AuthAccount> result = adminClient.login(split[1]);
            UserDetails userDetails = getUserDetails(split[0], result);
            return userDetails;
        } else if (split[0].equals(LoginConstant.USER)) {
            Result<AuthAccount> result = portalClient.login(split[1]);
            UserDetails userDetails = getUserDetails(split[0], result);
            return userDetails;
        }
        return null;
    }

    /**
     * 构建userdetails
     *
     * @param result 用户信息
     * @return Result 返回结果
     */
    private UserDetails getUserDetails(String userType, Result<AuthAccount> result) {
        if (result == null || result.getData() == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        AuthAccount account = result.getData();
        Set<String> dbAuthsSet = new HashSet<>();
        if (StringUtils.isNotBlank(account.getRoleName())) {
            dbAuthsSet.add(account.getRoleName());
        }

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));

        // 构造security用户
        return new SysUser(account.getId(),
                account.getUserName(),
                userType,
                userType + "-" + account.getMobile(),
                account.getPassword(),
                account.isEnabled(),
                account.isAccountNonExpired(),
                account.isCredentialsNonExpired(),
                account.isAccountNonLocked(),
                authorities,
                account.getBtns(),
                account.getCreateTime(),
                account.getTenantId());
    }
}
