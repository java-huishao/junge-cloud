package com.zhny.starter.log;

import com.zhny.starter.log.aspect.OperateLogAspect;
import com.zhny.starter.log.aspect.SystemLogAspect;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@RequiredArgsConstructor
@ConditionalOnWebApplication
public class LogConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect(ApplicationContext applicationContext) {
        return new OperateLogAspect(applicationContext);
    }

    @Bean
    public SystemLogAspect logAspect(ApplicationContext applicationContext) {
        return new SystemLogAspect(applicationContext);
    }
}
