package com.zhny.strategy.service;

import com.zhny.strategy.annonation.HandlerType;
import com.zhny.strategy.enums.FormTypeEnum;
import com.zhny.strategy.form.TypeForm;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 业务处理策略选择器
 *
 * @author pangu
 * 2020-10-5
 */
public class BusinessHandlerChooser<R, T extends TypeForm> {

    private Map<FormTypeEnum, BusinessHandler> businessHandlerMap;

    public void setBusinessHandlerMap(List<BusinessHandler> businessHandlers) {
        // 注入各类型的订单处理类
        businessHandlerMap = businessHandlers.stream().collect(
                Collectors.toMap(orderHandler -> AnnotationUtils.findAnnotation(orderHandler.getClass(), HandlerType.class).formTypeEnum(), v -> v, (v1, v2) -> v1));
    }

    public R handleBusiness(T t) {
        BusinessHandler<R, T> businessHandler = businessHandlerMap.get(t.getPayType());
        return businessHandler.handleBusiness(t);
    }
}
