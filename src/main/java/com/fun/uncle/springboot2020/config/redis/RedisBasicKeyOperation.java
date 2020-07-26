package com.fun.uncle.springboot2020.config.redis;

import java.util.concurrent.TimeUnit;

/**
 * @Description: redis 基础Key操作
 * @Author: fan
 * @DateTime: 2020/7/26 4:27 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public interface RedisBasicKeyOperation {

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    boolean expire(String key, long time);


    /**
     * 设置过期时间
     *
     * @param key
     * @param time
     * @param timeUnit
     * @return
     */
    boolean expire(String key, long time, TimeUnit timeUnit);


    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回-1代表为永久有效,-2表示已经过期
     */
    Long getExpire(String key);


    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    boolean hasKey(String key);


    /**
     * 删除缓存
     * @param key
     */
    void del(String key);


    /**
     * 删除缓存
     *
     * @param keys 可以传一个值 或多个
     */
    void del(String... keys);
}
