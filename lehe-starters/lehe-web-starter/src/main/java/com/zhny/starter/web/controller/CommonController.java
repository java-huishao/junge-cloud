package com.zhny.starter.web.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.system.RuntimeInfo;
import cn.hutool.system.SystemUtil;
import com.alibaba.fastjson.JSONObject;
import com.zhny.starter.common.constant.IdempotencyVersionNumber;
import com.zhny.starter.common.result.Result;
import com.zhny.starter.common.utils.SnowFlakeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author houqj
 * 2019-12-12 16:21
 */

@RestController
public class CommonController {

    @GetMapping(value = "/")
    public Result all() {
        return Result.success();
    }

    @GetMapping(value = "/csrf")
    public Result csrf() {
        return Result.success();
    }

    @GetMapping(value = "/systeminfo")
    public Result systeminfo() {
        JSONObject dict = new JSONObject();
        dict.put("jvmSpecInfo", SystemUtil.getJvmSpecInfo());
        dict.put("jvmInfo", SystemUtil.getJvmInfo());
        dict.put("javaSpecInfo", SystemUtil.getJavaSpecInfo());
        dict.put("javaInfo", SystemUtil.getJavaInfo());
        dict.put("javaRuntimeInfo", SystemUtil.getJavaRuntimeInfo());
        dict.put("osInfo", SystemUtil.getOsInfo());
        dict.put("userInfo", SystemUtil.getUserInfo());
        dict.put("hostInfo", SystemUtil.getHostInfo());
        RuntimeInfo runtimeInfo = SystemUtil.getRuntimeInfo();
        dict.put("freeMemory", runtimeInfo.getRuntime().freeMemory());
        dict.put("totalMemory", runtimeInfo.getRuntime().totalMemory());
        dict.put("availableProcessors", runtimeInfo.getRuntime().availableProcessors());
        dict.put("maxMemory", runtimeInfo.getRuntime().maxMemory());
        dict.put("usableMemory", runtimeInfo.getUsableMemory());
        dict.put("runtimeInfo", runtimeInfo.toString());
        return Result.success(dict);
    }

    @GetMapping(value = "/version")
    public Result version() {
        String version = SnowFlakeUtil.getId().toString();
        IdempotencyVersionNumber.versionMap.put(version, StrUtil.EMPTY);
        return Result.success(version);
    }
}
