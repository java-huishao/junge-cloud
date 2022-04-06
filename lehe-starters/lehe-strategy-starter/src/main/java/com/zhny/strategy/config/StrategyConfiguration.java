package com.zhny.strategy.config;

import com.zhny.strategy.service.BusinessHandler;
import com.zhny.strategy.service.BusinessHandlerChooser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 策略模式自动注入配置
 *
 * @author xuzhanfu
 * 2020-9-5
 */
@Configuration
public class StrategyConfiguration {

    @Bean
    public BusinessHandlerChooser businessHandlerChooser(List<BusinessHandler> businessHandlers) {
        BusinessHandlerChooser businessHandlerChooser = new BusinessHandlerChooser();
        businessHandlerChooser.setBusinessHandlerMap(businessHandlers);
        return businessHandlerChooser;
    }

}
