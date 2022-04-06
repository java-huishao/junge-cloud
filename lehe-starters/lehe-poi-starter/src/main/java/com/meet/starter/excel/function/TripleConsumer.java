package com.lehe.starter.excel.function;

/**
 * @author yudong
 * 2019/6/9
 */
@FunctionalInterface
public interface TripleConsumer<T, R, K> {
    /**
     * 对传入的参数进行操作
     *
     * @param t
     * @param r
     * @param k
     */
    void accept(T t, R r, K k);
}
