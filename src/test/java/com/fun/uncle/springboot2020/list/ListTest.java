package com.fun.uncle.springboot2020.list;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.ListUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Description: List的测试
 * @Author: Summer
 * @DateTime: 2020/8/20 4:10 下午
 * @Version: 0.0.1-SNAPSHOT
 */

public class ListTest {

    /**
     * 并集
     */
    @Test
    void unionTest() {
        List<Integer> integers1 = Lists.newArrayList(1, 2);

        List<Integer> integers2 = Lists.newArrayList(1, 2, 3);

        List<Integer> union = ListUtils.union(integers1, integers2);
        System.out.println(union);
    }

    /**
     * 交集
     */
    @Test
    void intersectionTest() {
        List<Integer> integers1 = Lists.newArrayList(1, 2);

        List<Integer> integers2 = Lists.newArrayList(1, 2, 3);

        List<Integer> intersection = ListUtils.intersection(integers1, integers2);
        System.out.println(intersection);
    }


    /**
     * 差集
     */
    @Test
    void subtractTest() {
        List<Integer> integers1 = Lists.newArrayList(1, 2, 4);

        List<Integer> integers2 = Lists.newArrayList(1, 2, 3);

        List<Integer> subtract = ListUtils.subtract(integers1, integers2);
        System.out.println(subtract);
        System.out.println(integers1);
        System.out.println(integers2);
    }



}
