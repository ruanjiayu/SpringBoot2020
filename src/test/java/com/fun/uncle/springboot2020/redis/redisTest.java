package com.fun.uncle.springboot2020.redis;

import com.fun.uncle.springboot2020.SpringBoot2020ApplicationTests;
import com.fun.uncle.springboot2020.config.redis.RedisBasicOperation;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: fan
 * @DateTime: 2020/7/26 2:52 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class redisTest extends SpringBoot2020ApplicationTests {

    @Autowired
    private RedisBasicOperation redisBasicOperation;

    @Test
    public void expireTest() {
        System.out.println(redisBasicOperation.getExpire("haha"));
    }

    @Test
    public void listTest() {
        redisBasicOperation.leftPushAll("fan", Lists.list("1", "2", "3", "4", "5"));
    }

    @Test
    public void listIndexTest() {
        System.out.println(redisBasicOperation.index("fan", 0));
    }

    @Test
    public void listRangeTest() {
        System.out.println(redisBasicOperation.range("fan", 0, 0));
    }

}
