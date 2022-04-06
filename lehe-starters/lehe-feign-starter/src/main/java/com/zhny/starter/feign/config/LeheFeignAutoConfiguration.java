package com.zhny.starter.feign.config;

import com.zhny.starter.feign.endpoint.FeignClientEndpoint;
import feign.Feign;
import feign.Logger;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.LeheFeignClientsRegistrar;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Feign配置类
 *
 * @author xuzhanfu
 */
@Configuration
@ConditionalOnClass(Feign.class)
@Import(LeheFeignClientsRegistrar.class)
@AutoConfigureAfter(EnableFeignClients.class)
public class LeheFeignAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnAvailableEndpoint
    public FeignClientEndpoint feignClientEndpoint(ApplicationContext context) {
        return new FeignClientEndpoint(context);
    }

    /**
     * feign 日志记录级别
     * NONE：        【性能最佳，适用于生产】无日志记录（默认）
     * BASIC：       【适用于生产环境追踪问题】只记录请求方法和 url 以及响应状态代码和执行时间。
     * HEADERS：     【记录BASIC基础上】记录请求和响应头的基本信息。
     * FULL：        【比较适用于开发及测试环境定位问题】记录请求和响应的头、正文和元数据。
     *
     * @return Logger.Level
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.HEADERS;
    }


//    @Bean
//    @Primary
//    public MateHystrixTargeter mateFeignTargeter() {
//        return new MateHystrixTargeter();
//    }
}
