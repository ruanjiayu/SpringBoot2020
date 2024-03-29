package com.fun.uncle.springboot2020.config.redis.facade;

import java.util.List;
import java.util.Set;

/**
 * @Description: redis 基础set操作
 * @Author: fan
 * @DateTime: 2020/7/26 3:49 下午
 * @Version: 0.0.1-SNAPSHOT
 */

public interface RedisSetFacade {


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

    /**
     * 从set内中随机弹出一个值
     * @param key
     * @return
     */
    String getSPop(String key);

    /**
     * 使用pipeline批量插入redis的set值
     * @param key
     * @param values
     */
    void batchInsertSet(String key, List<String> values);

}
