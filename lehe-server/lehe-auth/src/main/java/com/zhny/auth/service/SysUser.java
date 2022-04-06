package com.zhny.auth.service;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Houqijun
 * 2018/8/20
 * 扩展用户信息
 */
public class SysUser extends User implements Serializable {
    /**
     * 用户ID
     */
    @Getter
    private final String id;
    @Getter
    private final String name;
    @Getter
    private final String userType;
    @Getter
    private final String openTime;
    @Getter
    private final String tenantId;
    /**
     * 用户可操作的按钮路径
     */
    @Getter
    private final List<String> btns;

    public SysUser(String id, String name, String userType, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, List<String> btns, String openTime, String tenantId) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.btns = btns;
        this.openTime = openTime;
        this.tenantId = tenantId;
    }
}
