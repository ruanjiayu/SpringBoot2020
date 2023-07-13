package com.fun.uncle.springboot2020.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 环状数组
 * @Author: summer
 * @CreateDate: 2023/5/22 15:39
 * @Version: 1.0.0
 */
public class CircularArray<T> {

    private final Object[] items;

    private final int size;

    private AtomicInteger head = new AtomicInteger(0);

    private AtomicInteger tail = new AtomicInteger(0);

    /**
     * 当前遍历到那个位置
     */
    private int currentIndex = 0;

    public CircularArray(int size) {
        this.items = new Object[size];
        this.size = size;
    }

    public void put(T item) {
        int pos = tail.getAndIncrement() % size;
        items[pos] = item;
        if (tail.get() - head.get() > size) {
            head.incrementAndGet();
        }
    }

    public T get(int index) {
        int pos = (head.get() + index) % size;
        return (T) items[pos];
    }


    public T getFixedPosition(int index) {
        int pos = index % size;
        return (T) items[pos];
    }

    /**
     * 实际占用大小
     *
     * @return
     */
    public int size() {
        return tail.get() - head.get();
    }

    /**
     * 得到容量大小
     *
     * @return
     */
    public int getCapacitySize() {
        return size;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public static void main(String[] args) {
        CircularArray<Integer> array = new CircularArray<>(3);
        array.put(1);
        array.put(2);
        array.put(3);

        System.out.println(array.get(0)); // 输出 1
        System.out.println(array.get(1)); // 输出 2
        System.out.println(array.get(2)); // 输出 3
        System.out.println("===============");

        array.put(4); // 元素 1 被弹出，添加新元素 4
        System.out.println(array.get(0)); // 输出 2
        System.out.println(array.get(1)); // 输出 3
        System.out.println(array.get(2)); // 输出 4
        System.out.println("===============");

        int currentIndex = array.getCurrentIndex();
        // 遍历环状数组
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(currentIndex));

            // 重新回到数组头部
            if (currentIndex == 255) {
                currentIndex = 0;
            } else {
                currentIndex++;
            }
        }
    }
}