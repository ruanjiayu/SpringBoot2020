package com.fun.uncle.springboot2020.wrapper;

import java.util.function.Function;

/**
 * @Description: 对check Exception 进行捕获并进行转换
 * @Author: Summer
 * @DateTime: 2022/3/21 11:50 上午
 * @Version: 0.0.1-SNAPSHOT
 */
public class CheckedWrappableFunctionMapper {

    public static <T, R> Function<T, R> wrap(CheckedWrappableFunction<T, R> wrappableFunction) {
        return t -> {
            try {
                return wrappableFunction.apply(t);
            } catch (Exception e) {
                throw new RuntimeException();
            }
        };
    }
}
