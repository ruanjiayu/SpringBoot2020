package com.fun.uncle.springboot2020.config.redis;

import java.util.Set;

/**
 * @Description: redis 基础set操作
 * @Author: fan
 * @DateTime: 2020/7/26 3:49 下午
 * @Version: 0.0.1-SNAPSHOT
 */

public interface RedisBasicSetOperation {


    /**
     * 向set类型中添加新的值
     * @param key key
     * @param value 值
     */
     void addSet(String key, String value);

    /**
     * 判断set中是否包含对应的值
     * @param key key
     * @param value 值
     * @return
     */
     boolean setContains(String key, String value);

    /**
     * 获取set中的值
     * @param key key
     * @return 获取值
     */
    Set<String> getSet(String key);


    /**
     * 获取set中的范围大小
     * @param key
     * @return
     */
    Long getSetSize(String key);

}
