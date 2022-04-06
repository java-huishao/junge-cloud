package com.zhny.auth.feign.fallback;

import com.zhny.auth.feign.AdminClient;
import com.zhny.common.model.AuthAccount;
import com.zhny.starter.common.result.Result;
import com.zhny.starter.common.result.ResultEnum;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program: mycloud-admin
 * @description:
 * @author: houqijun
 * @create: 2019-03-03 10:34
 **/
@Slf4j
@Component
public class AdminClientFallbackImpl implements AdminClient {

    @Setter
    private Throwable cause;

    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     * @return Result 返回结果 Result
     */
    @Override
    public Result<AuthAccount> login(String username) {
        return Result.fail(ResultEnum.FEIGN_ERROR);
    }
}
