package com.fun.uncle.springboot2020.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description: redis 分布式锁
 * @Author: fan
 * @DateTime: 2020/7/23 10:58 下午
 * @Version: 0.0.1-SNAPSHOT
 */
@Component
@Slf4j
public class RedisDistributedLock {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String UNLOCK_LUA = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    /**
     * 锁1分钟
     * @param key redis中key
     * @param requestId value
     * @return
     */
    public boolean setLock(String key, String requestId) {
        return setLock(key, requestId, 60000L);
    }


    /**
     * 上锁
     * @param key redis中key
     * @param requestId  value
     * @param expire 过期时间
     * @return
     */
    public boolean setLock(String key, String requestId, long expire) {
        SetParams setParams = new SetParams().nx().px(expire);
        try {
            RedisCallback<String> callback = (connection) -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.set(key, requestId, setParams);
            };
            String result = stringRedisTemplate.execute(callback);
            return StringUtils.isNotEmpty(result);
        } catch (Exception e) {
            log.error("【Redis分布式锁】设置锁失败", e);
        }
        return false;
    }

    /**
     *
     * @param key
     * @return
     */
    public String get(String key) {
        try {
            RedisCallback<String> callback = (connection) -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.get(key);
            };
            return stringRedisTemplate.execute(callback);
        } catch (Exception e) {
            log.error("【Redis分布式锁】获取值失败", e);
        }
        return "";
    }

    /**
     * 解锁
     * @param key
     * @param requestId
     * @return
     */
    public boolean releaseLock(String key, String requestId) {
        // 释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
        if (Objects.isNull(key)) {
            return false;
        }
        try {
            List<String> keys = new ArrayList<>();
            keys.add(key);
            List<String> args = new ArrayList<>();
            args.add(requestId);

            // 使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
            // spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本
            RedisCallback<Long> callback = (connection) -> {
                Object nativeConnection = connection.getNativeConnection();
                // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                // 集群模式
                if (nativeConnection instanceof JedisCluster) {
                    return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }
                // 单机模式
                else if (nativeConnection instanceof Jedis) {
                    return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }
                return 0L;
            };
            Long result = stringRedisTemplate.execute(callback);

            return result != null && result > 0;
        } catch (Exception e) {
            log.error("【Redis分布式锁】解锁失败", e);
        } finally {
            // 清除掉ThreadLocal中的数据，避免内存溢出
            //lockFlag.remove();
        }
        return false;
    }
}
