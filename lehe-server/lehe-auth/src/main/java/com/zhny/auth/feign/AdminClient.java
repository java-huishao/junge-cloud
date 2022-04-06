package com.zhny.auth.feign;

import com.zhny.auth.feign.fallback.AdminClientFallbackImpl;
import com.zhny.common.model.AuthAccount;
import com.zhny.starter.common.constant.SystemConstant;
import com.zhny.starter.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户中心对外开放接口
 */
@FeignClient(name = SystemConstant.LEHE_ADMIN, value = SystemConstant.LEHE_ADMIN, fallback = AdminClientFallbackImpl.class)
public interface AdminClient {
    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户电话
     * @return Result 返回结果 Result
     */
    @GetMapping(value = "/admin-account/login", produces = "application/json;charset=UTF-8")
    Result<AuthAccount> login(@RequestParam("username") String username);
}
