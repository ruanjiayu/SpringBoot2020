package com.fun.uncle.springboot2020.config.redis;

/**
 * @Description: redis 基础Hash操作
 * @Author: fan
 * @DateTime: 2020/7/26 4:24 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public interface RedisBasicHashOperation {


    /**
     * 将值放入redis的hash类型中
     *
     * @param key
     * @param hashKey
     * @param hashValue
     */
    void putHash(String key, String hashKey, String hashValue);


    /**
     * 从redis中获取hash类型中的值
     *
     * @param key
     * @param hashKey
     * @return
     */
    String getHash(String key, String hashKey);
}
