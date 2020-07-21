package com.fun.uncle.springboot2020.utils;

import java.util.Random;

/**
 * @Description: 创建随机数
 * @Author: Summer
 * @DateTime: 2020/7/21 3:08 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class RandomUtil {

    private static final Random RANDOM_NUMBER_GENERATOR = new Random();


    /**
     * 获得随机正整数[0, bound)
     * @param bound
     * @return
     */
    public static int getRandom(int bound) {
        return RANDOM_NUMBER_GENERATOR.nextInt(bound);
    }
}
