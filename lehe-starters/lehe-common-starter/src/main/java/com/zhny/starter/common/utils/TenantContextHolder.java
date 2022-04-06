package com.zhny.starter.common.utils;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.zhny.starter.common.oauth2.Oauth2AccessUser;
import lombok.experimental.UtilityClass;

/**
 * @author houqijun
 * 2018/10/4
 * 租户工具类
 */
@UtilityClass
public class TenantContextHolder {

    private final ThreadLocal<Oauth2AccessUser> thread_lcoal_user = new TransmittableThreadLocal<>();

    public Oauth2AccessUser getUser() {
        return thread_lcoal_user.get();
    }

    public void setUser(Oauth2AccessUser oauth2accessToken) {
        thread_lcoal_user.set(oauth2accessToken);
    }

    public void clear() {
        thread_lcoal_user.remove();
    }
}
