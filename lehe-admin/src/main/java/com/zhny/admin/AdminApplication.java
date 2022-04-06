package com.zhny.admin;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.lehe.starter.mybatisplus.annotations.EnableDiboot;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author houqj
 * 2020-01-07 14:37
 */
@EnableAsync
@EnableCaching
@MapperScan("com.lehe.admin.mapper")
@EnableDiboot
@SpringBootApplication
@EnableDiscoveryClient
@EnableMethodCache(basePackages = "com.lehe.admin.service")
@EnableCreateCacheAnnotation
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class);
    }
}
