package com.fun.uncle.springboot2020.list;

import com.alipay.api.domain.Person;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections4.ListUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

        HashMap<Object, Object> hashMap = new HashMap<>();
        System.out.println(hashMap.get(null));
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


    /**
     * 集合出现空字符如何进行比较。
     * nullsFirst 放在头部
     * nullsLast 放在尾部
     *
     */
    @Test
    void collectTest() {
        List<Item> dataList = Arrays.asList(new Item("A", "a"), new Item("B", "b"), new Item("B", null), new Item(null, null));
//        List<Item> collect = dataList.stream().sorted(Comparator.comparing(Item::getFirst).
//                thenComparing(Item::getSecond)).collect(Collectors.toList());


        List<Item> collect = dataList.stream().sorted(Comparator.comparing(Item::getFirst, Comparator.nullsFirst(String::compareTo)).
                thenComparing(Item::getSecond, Comparator.nullsFirst(String::compareTo))).collect(Collectors.toList());

        System.out.println(collect);

    }


    @Data
    static class Item {
        private String first;

        private String second;

        public Item(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }

}
