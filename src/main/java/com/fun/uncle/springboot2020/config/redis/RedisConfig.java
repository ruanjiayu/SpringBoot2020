package com.fun.uncle.springboot2020.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Description: redis 相关配置
 * @Author: Summer
 * @DateTime: 2020/7/21 11:54 上午
 * @Version: 0.0.1-SNAPSHOT
 */
@Configuration
public class RedisConfig {

//    @Bean
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
//        return stringRedisTemplate;
//    }
}
