package com.fun.uncle.springboot2020.config.redis.facade;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

/**
 * @Description: redis 基础ZSet操作
 * @Author: fan
 * @DateTime: 2020/7/26 3:53 下午
 * @Version: 0.0.1-SNAPSHOT
 */

 public interface RedisZSetFacade {


    /**
     * 将ZSet的分值加一
     *
     * @param key   key
     * @param value 值
     * @return 新增后的分值
     */
    Double incrementScoreZSet(String key, String value);

    /**
     * 将ZSet的分值加上指定值
     *
     * @param key   key
     * @param value 值
     * @param delta 分值的新增
     * @return 新增后的分值
     */
    Double incrementScoreZSet(String key, String value, double delta);


    /**
     * 获取对应的score
     *
     * @param key   key
     * @param value 值
     * @return
     */
    Double getScoreZSet(String key, String value);


    /**
     * 设置对应的值的score
     *
     * @param key   key
     * @param value 对应的属性
     * @param score 分值
     * @return 新增为true, 更新为false
     */
    Boolean setScoreZSet(String key, String value, Double score);


    /**
     * 找到对应的值的坐标
     *
     * @param key   key
     * @param value 值
     * @param desc  是否是倒叙
     * @return
     */
    Long getRankZSet(String key, String value, boolean desc);


    /**
     * 获取对应范围内的值逆序的形式
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<ZSetOperations.TypedTuple<String>> getReverseRangeWithScoresZSet(String key, Long start, Long end);


    /**
     * 获取对应坐标的score
     * @param key key
     * @param index 坐标
     * @return
     */
     Double getReverseRangeWithScoresZSet(String key, Long index);


    /**
     * 获取ZSet中的长度
     * @param key key
     * @return
     */
     Long getZSetSize(String key);

}
