package com.fun.uncle.springboot2020.config.redis;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis 基础list操作
 * @Author: fan
 * @DateTime: 2020/7/26 4:37 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public interface RedisBasicListOperation {


    /**
     * 从右边向列表内推入数据
     *
     * @param key   key
     * @param value 值
     */
    void rightPush(String key, String value);


    /**
     * 从右边向列表内推入批量数据
     *
     * @param key    key
     * @param values 值
     */
    void rightPushAll(String key, List<String> values);


    /**
     * 从左边向列表内推入批量数据
     *
     * @param key    key
     * @param values 列表
     */
    void leftPushAll(String key, List<String> values);


    /**
     * 从左边推出相关值
     *
     * @param key key
     * @return
     */
    String leftPop(String key);

    /**
     * 获取对应坐标的值 不会将其推出
     *
     * @param key   key
     * @param index 坐标
     * @return
     */
    String index(String key, long index);


    /**
     * 从右边推出对应的元素
     *
     * @param key key
     * @return
     */
    String rightPop(String key);


    /**
     * 查询list中的个数
     *
     * @param key key
     * @return
     */
    Long listSize(String key);


    /**
     * 从list中展示对应范围内中的内容
     *
     * @param key   key
     * @param start 数据的坐标
     * @param end   数据的坐标
     * @return 例如 0，0 那么表示第一个元素。0，1表示两个元素
     */
    List<String> range(String key, Integer start, Integer end);


    /**
     * 将map类型直接放入redis
     *
     * @param key      key
     * @param map      map
     * @param time     时间
     * @param timeUnit 时间单位
     */
    void putAllHash(String key, Map<String, String> map, Long time, TimeUnit timeUnit);
}
