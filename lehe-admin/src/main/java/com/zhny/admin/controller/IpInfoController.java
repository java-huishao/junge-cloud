package com.zhny.admin.controller;

import com.zhny.starter.common.result.Result;
import com.lehe.starter.log.common.IpInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 2020
 * 2021-09-23 10:36
 */
@Slf4j
@RestController
@RequestMapping("/common/ip")
public class IpInfoController {

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Result<Object> upload(HttpServletRequest request) {
        String ipCity = IpInfoUtil.getIpCity(request.getRemoteAddr());
        return Result.success(ipCity);
    }
}
