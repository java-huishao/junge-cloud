package com.zhny.strategy.service;

/**
 * 业务处理策略接口
 *
 * @author pangu
 * 2020-10-5
 */
public interface BusinessHandler<R, T> {

    /**
     * 业务处理
     *
     * @param t 业务实体返回参数
     * @return R　结果
     */
    R handleBusiness(T t);
}
