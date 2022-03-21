package com.fun.uncle.springboot2020.wrapper;

/**
 * @Description: 优化代码的Checked Exception。比如IOException
 * @Author: Summer
 * @DateTime: 2022/3/21 11:49 上午
 * @Version: 0.0.1-SNAPSHOT
 */
@FunctionalInterface
public interface CheckedWrappableFunction<T, R>   {

    R apply(T t) throws Exception;
}
