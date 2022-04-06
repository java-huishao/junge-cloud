package com.zhny.starter.web.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 2020
 * @date 2022-02-23 9:55
 */
@Data
@ToString
@Component
@ConfigurationProperties(prefix = "lehe")
public class LeheBootProperties {

    /**
     * 是否启用sql分析插件，默认false
     */
    private boolean performancesql = false;
    /**
     * 允许sql执行的最大时间，超出则报错
     */
    private Long performancesqlMaxTime = 200L;
}
