package com.fun.uncle.springboot2020.config.redis.facade;

/**
 * @Description: 高级功能
 * @Author: summer
 * @CreateDate: 2023/1/11 11:43
 * @Version: 1.0.0
 */
public interface RedisAdvancedFacade {

    /**
     * 设置位图指定位置为1
     *
     * @param key    key
     * @param offset 角标 从0开始
     * @param exist  存在
     */
    void setBit(String key, Long offset, boolean exist);


    /**
     * 获取位图中对应位置的值
     *
     * @param key
     * @param offset
     * @return
     */
    boolean getBit(String key, Long offset);


    /**
     * 统计位图中1的个数
     *
     * @param key
     * @return
     */
    long bitCount(String key);
}
