package com.fun.uncle.springboot2020.config.redis;

import com.fun.uncle.springboot2020.config.redis.facade.RedisAdvancedFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description: 缓存的高级操作
 * @Author: summer
 * @CreateDate: 2023/1/11 13:19
 * @Version: 1.0.0
 */
@Component
@Slf4j
public class RedisAdvancedOperation implements RedisAdvancedFacade {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void setBit(String key, Long offset, boolean exist) {
        stringRedisTemplate.opsForValue().setBit(key, offset, exist);
    }

    @Override
    public boolean getBit(String key, Long offset) {
        return stringRedisTemplate.opsForValue().getBit(key, offset);
    }

    @Override
    public long bitCount(String key) {
        return stringRedisTemplate.execute((RedisCallback<Long>) conn -> conn.bitCount(key.getBytes()));
    }
}
