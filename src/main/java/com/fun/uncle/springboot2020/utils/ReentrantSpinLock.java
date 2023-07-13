package com.fun.uncle.springboot2020.utils;


import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description: 自旋锁
 * @Author: summer
 * @CreateDate: 2023/5/12 09:17
 * @Version: 1.0.0
 */
public class ReentrantSpinLock {

    private static final Logger logger = LoggerUtil.COMMON_INFO;

    private static AtomicReference<Object> sign = new AtomicReference<>();

    /**
     * @param t
     * @param reentrantFlag 是否可以重入 true:可以重新进入 false：表示不可以
     * @param <T>
     */
    public static <T> void lock(T t, boolean reentrantFlag) {
        // 若可重入标志为true, 且若尝试加锁的对象和已加的锁中的对象相同，可重入,并加锁成功
        if (reentrantFlag && t == sign.get()) {
            return;
        }
        //If the lock is not acquired, it can be spun through CAS
        while (!sign.compareAndSet(null, t)) {
            // DO nothing
//            LoggerUtil.info(logger, "自旋一会");
            // 让出CPU给其他线程执行,仅仅是为了让其他线程有机会获得CPU时间片执行，而不会对整体性能产生显著影响。
            LockSupport.parkNanos(1);
        }
    }

    /**
     * 使用可选的超时时间获取锁。
     *
     * @param t              与锁相关联的对象
     * @param reentrantFlag  是否允许重入锁
     * @param timeoutMillis  超时时间（毫秒）
     * @param <T>            对象的类型
     * @return 如果成功获取锁，则返回true；如果锁获取超时，则返回false
     */
    public static <T> boolean lock(T t, boolean reentrantFlag, long timeoutMillis) {
        // 如果reentrantFlag为true，并且尝试获取锁的对象与已锁定的对象相同，则是重入锁
        if (reentrantFlag && t == sign.get()) {
            return true;
        }

        long startTime = System.currentTimeMillis();
        long endTime = startTime + timeoutMillis;

        // 在指定的超时时间内尝试获取锁
        while (System.currentTimeMillis() < endTime) {
            if (sign.compareAndSet(null, t)) {
                return true;
            }

            // 通过让出CPU时间片给其他线程执行，来让其他线程有机会执行
            Thread.yield();
        }

        return false; // 锁获取超时
    }



    /**
     * 解锁
     *
     * @param t
     * @param <T>
     */
    public static <T> void unlock(T t) {
        // 锁的线程和目前的线程相等时，才允许释放锁
        if (t == sign.get()) {
            sign.compareAndSet(t, null);
        }
    }
}