package com.fun.uncle.springboot2020.controller;

import com.fun.uncle.springboot2020.cache.CacheKey;
import com.fun.uncle.springboot2020.config.redis.RedisDistributedLock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Description:
 * @Author: fan
 * @DateTime: 2020/7/23 11:00 下午
 * @Version: 0.0.1-SNAPSHOT
 */
@RestController
@RequestMapping(value = "redisLock")
@Api(tags = "分布式锁")
public class RedisLockController {


    @Autowired
    private RedisDistributedLock redisDistributedLock;

    @GetMapping("/lock")
    @ApiOperation(value = "加锁")
    public String lock() {
        String lockKey = CacheKey.TEST_LOCK;
        String requestId = UUID.randomUUID().toString();
        boolean isGetLock;
        isGetLock = redisDistributedLock.setLock(lockKey, requestId);
        try {
            if (!isGetLock) {
                System.out.println(Thread.currentThread().getName() +  "加锁失败, 请稍后");
                return "加锁失败, 请稍后";
            }
            Thread.sleep(1000L);
            return "加锁成功";
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (isGetLock) {
                System.out.println((Thread.currentThread().getName() +  "已经🔓"));
                redisDistributedLock.releaseLock(lockKey, requestId);
            }
        }
        return "加锁失败, 请稍后@@@";
    }

}
