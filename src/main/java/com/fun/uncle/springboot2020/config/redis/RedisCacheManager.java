package com.fun.uncle.springboot2020.config.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fun.uncle.springboot2020.utils.RandomUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @Description: redis相关的控制
 * @Author: Summer
 * @DateTime: 2020/7/21 2:37 下午
 * @Version: 0.0.1-SNAPSHOT
 */
@Component
@Slf4j
public class RedisCacheManager {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 如果redis中存在值，那么直接将其取出，不然就是相应操作后，将其值放入缓存
     * @param supplier 相关操作
     * @param resultClazz 返回的类型
     * @param key 缓存值key
     * @param time 时间
     * @param timeUnit 时间单位 TimeUnit.SECONDS
     * @param <T> 泛形
     * @return
     */
    public <T> T assembleRedisObject(Supplier<T> supplier, Class<T> resultClazz, String key, Long time, TimeUnit timeUnit) {
        boolean isString = String.class == resultClazz;
        // 判断是否为空
        if (StringUtils.isBlank(key)) {
            return null;
        }
        String value = getString(key);
        if (StringUtils.isBlank(value)) {
            T result = supplier.get();
            if (Objects.nonNull(result)) {
                if (isString) {
                    setString(key, (String) result, time, timeUnit);
                } else {
                    setString(key, JSON.toJSONString(result), time, timeUnit);
                }
            }
            return result;
        }
        // 解决字符串中包含%导致json序列号失败的问题
        return isString ? (T) value : JSON.parseObject(value, resultClazz);
    }


    public <T> List<T> assembleRedisList(Supplier<List<T>> supplier, Class<T> resultClazz, String key, Long time) {
        return assembleRedisList(supplier, resultClazz, key, false, time, TimeUnit.SECONDS);
    }

    public <T> List<T> assembleRedisList(Supplier<List<T>> supplier, Class<T> resultClazz, String key, Long time, TimeUnit timeUnit) {
        return assembleRedisList(supplier, resultClazz, key, false, time, timeUnit);
    }

    public <T> List<T> assembleRedisList(Supplier<List<T>> supplier, Class<T> resultClazz, String key, boolean cacheNull, Long time) {
        return assembleRedisList(supplier, resultClazz, key, cacheNull, time, TimeUnit.SECONDS);
    }


    /**
     *  如果redis中存在值，那么直接将其取出，不然就是相应操作后，将其值放入缓存
     * @param supplier 对应的操作
     * @param resultClazz 返回的类型
     * @param key 缓存的key
     * @param cacheNull 是否存储为null
     * @param time 时间
     * @param timeUnit 时间单位
     * @param <T>
     * @return
     */
    public <T> List<T> assembleRedisList(Supplier<List<T>> supplier, Class<T> resultClazz, String key, boolean cacheNull, Long time, TimeUnit timeUnit) {
        // 判断是否为空
        if (StringUtils.isBlank(key)) {
            return Lists.newArrayList();
        }
        String value = getString(key);
        if (StringUtils.isBlank(value)) {
            List<T> result = supplier.get();
            if (!Objects.isNull(result)) {
                setString(key, JSON.toJSONString(result), time, timeUnit);
            } else if (cacheNull) {
                setString(key, "[]", time, timeUnit);
            }
            return Optional.ofNullable(result).orElse(Lists.newArrayList());
        }
        return JSON.parseArray(value, resultClazz);
    }

    public Map<String, String> assembleRedisMap(Supplier<Map<String, String>> supplier, String key, Long time) {
        return assembleRedisMap(supplier, String.class, String.class, key, false, time, TimeUnit.SECONDS);
    }

    public <K, V> Map<K, V> assembleRedisMap(Supplier<Map<K, V>> supplier, Class<K> keyType,
                                             Class<V> valueType, String key, Long time) {
        return assembleRedisMap(supplier, keyType, valueType, key, false, time, TimeUnit.SECONDS);
    }

    public <K, V> Map<K, V> assembleRedisMap(Supplier<Map<K, V>> supplier, Class<K> keyType,
                                             Class<V> valueType, String key, boolean cacheNull, Long time) {
        return assembleRedisMap(supplier, keyType, valueType, key, cacheNull, time, TimeUnit.SECONDS);
    }

    /**
     *
     * @param supplier 对应的操作
     * @param keyType key的类型(基本是String)
     * @param valueType 值的类型
     * @param key key的名字
     * @param cacheNull 是否存储null
     * @param time 时间
     * @param timeUnit 时间单位
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> Map<K, V> assembleRedisMap(Supplier<Map<K, V>> supplier, Class<K> keyType,
                                             Class<V> valueType, String key, boolean cacheNull, Long time, TimeUnit timeUnit) {
        // 判断是否为空
        if (StringUtils.isBlank(key)) {
            return Maps.newHashMap();
        }
        String value = getString(key);
        if (StringUtils.isBlank(value)) {
            Map<K, V> result = supplier.get();
            if (!Objects.isNull(result)) {
                setString(key, JSON.toJSONString(result), time, timeUnit);
            } else if (cacheNull) {
                setString(key, "{}", time, timeUnit);
            }
            return Optional.ofNullable(result).orElse(Maps.newHashMap());
        }
        return JSON.parseObject(value, new TypeReference<Map<K, V>>(keyType, valueType) {
        });
    }


    /**
     * 获取redis中的值
     * @param key
     * @return
     */
    public String getString(String key) {
        try {
            return key == null ? null : stringRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("【redis异常】", e);
            return null;
        }
    }


    /**
     * 设置 redis 的值
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     * @return
     */
    public boolean setString(String key, String value, Long time, TimeUnit timeUnit) {
        if (StringUtils.isAnyBlank(key, value)) {
            return false;
        }
        try {
            if (time > 0) {
                if (TimeUnit.SECONDS == timeUnit) {
                    // 解决服务雪崩情况
                    time += RandomUtil.getRandom(60);
                }
                stringRedisTemplate.opsForValue().set(key, value, time, timeUnit);
            } else {
                stringRedisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("【redis异常】", e);
            return false;
        }
    }


    /**
     *
     * @param supplier
     * @param resultClazz
     * @param key
     * @param hashKey
     * @param cacheNull
     * @param time
     * @param <T>
     * @return
     */
    public <T> List<T> assembleRedisHashForList(Supplier<List<T>> supplier, Class<T> resultClazz, String key, String hashKey, boolean cacheNull, Long time) {
        if (StringUtils.isBlank(key)) {
            return Lists.newArrayList();
        }
        String value = getHash(key, hashKey);
        if (StringUtils.isBlank(value)) {
            List<T> result = supplier.get();
            if (!CollectionUtils.isEmpty(result)) {
                putHash(key, hashKey, JSON.toJSONString(result));
                expire(key, time);
            } else if (cacheNull) {
                putHash(key, hashKey, "[]");
                expire(key, time);
            }
            return Optional.ofNullable(result).orElse(Lists.newArrayList());
        }
        return JSON.parseArray(value, resultClazz);
    }


    public void putHash(String key, String hashKey, String hashValue) {
        if (StringUtils.isAnyBlank(key, hashKey, hashValue)) {
            return;
        }
        try {
            stringRedisTemplate.opsForHash().put(key, hashKey, hashValue);
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
    }

    public String getHash(String key, String hashKey) {
        if (StringUtils.isAnyBlank(key, hashKey)) {
            return null;
        }
        try {
            return (String) stringRedisTemplate.opsForHash().get(key, hashKey);
        } catch (Exception e) {
            log.error("【redis异常】", e);
            return null;
        }
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        return expire(key, time, TimeUnit.SECONDS);
    }

    public boolean expire(String key, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                return stringRedisTemplate.expire(key, time, timeUnit);
            }
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
        return false;
    }

}
