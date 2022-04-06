package com.zhny.starter.feign.fallback;

import feign.Target;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.FeignErrorDecoderFactory;

/**
 * 默认fallback，减少必要的编写fallback类
 *
 * @param <T>
 */
@AllArgsConstructor
public class LeheFallbackFactory<T> implements FeignErrorDecoderFactory {
    private final Target<T> target;

    @Override
    public ErrorDecoder create(Class<?> type) {
        final Class<T> targetType = target.type();
        final String targetName = target.name();
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(targetType);
//        enhancer.setUseCache(true);
//        enhancer.setCallback(new com.lehe.starter.feign.fallback.LeheFeignFallback<>(targetType, targetName, type));
        return null;
    }
}
