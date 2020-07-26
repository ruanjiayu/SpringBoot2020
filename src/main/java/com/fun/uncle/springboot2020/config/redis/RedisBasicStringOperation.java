package com.fun.uncle.springboot2020.config.redis;

import java.util.concurrent.TimeUnit;

/**
 * @Description: redis 基础String操作
 * @Author: fan
 * @DateTime: 2020/7/26 4:21 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public interface RedisBasicStringOperation {

    /**
     * 获取redis中的值
     *
     * @param key
     * @return
     */
    String getString(String key);


    /**
     * 设置 redis 的值
     *
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     * @return
     */
    boolean setString(String key, String value, Long time, TimeUnit timeUnit);


    /**
     * 设置自增的值
     *
     * @param key
     * @param delta
     * @return
     */
    Long increment(String key, long delta);


    /**
     * 设置自增的值并且设置过期时间
     *
     * @param key
     * @param delta
     * @param time
     * @return
     */
    Long increment(String key, long delta, long time);
}
