package com.fun.uncle.springboot2020.redis;

import com.fun.uncle.springboot2020.ApplicationTests;
import com.fun.uncle.springboot2020.config.redis.RedisAdvancedOperation;
import com.fun.uncle.springboot2020.config.redis.RedisBasicOperation;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: fan
 * @DateTime: 2020/7/26 2:52 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class redisTest extends ApplicationTests {

    @Autowired
    private RedisBasicOperation redisBasicOperation;

    @Autowired
    private RedisAdvancedOperation redisAdvancedOperation;

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


    @Test
    public void stringTest() {
        redisBasicOperation.setString("20230111:string:china", "阮佳裕haha", 1L, TimeUnit.DAYS);
        redisBasicOperation.setString("20230111:string:num", "124", 1L, TimeUnit.DAYS);
        redisBasicOperation.setString("20230111:string:en", "haha", 1L, TimeUnit.DAYS);

        System.out.println(redisBasicOperation.getString("20230111:string:china"));
        System.out.println(redisBasicOperation.getString("20230111:string:num"));
        System.out.println(redisBasicOperation.getString("20230111:string:en"));
    }


    @Test
    public void bitSet() {
        redisAdvancedOperation.setBit("20230111_bit", 0L, true);
        redisAdvancedOperation.setBit("20230111_bit", 2L, true);
        redisAdvancedOperation.setBit("20230111_bit", 3L, true);
    }

    @Test
    public void bitGet() {
        System.out.println(redisAdvancedOperation.getBit("20230111_bit", 0L));
        System.out.println(redisAdvancedOperation.getBit("20230111_bit", 1L));
        System.out.println(redisAdvancedOperation.getBit("20230111_bit", 2L));
        System.out.println(redisAdvancedOperation.getBit("20230111_bit", 3L));
        System.out.println(redisAdvancedOperation.getBit("20230111_bit", 4L));
        System.out.println(redisAdvancedOperation.getBit("20230111_bit", 5L));
        System.out.println(redisAdvancedOperation.getBit("20230111_bit", 6L));
    }

    @Test
    public void bitCount() {
        System.out.println(redisAdvancedOperation.bitCount("20230111_bit"));
    }




}
