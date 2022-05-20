package com.fun.uncle.springboot2020.utils;

import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @Description: 事务控制
 * @Author: Summer
 * @DateTime: 2022/5/20 11:19 上午
 * @Version: 0.0.1-SNAPSHOT
 */
public class TransactionUtil {

    /**
     * 事务提交之前进行操作
     *
     * @param runnable
     */
    public static void beforeCommit(Runnable runnable) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void beforeCommit(boolean readOnly) {
                runnable.run();
            }
        });
    }

    /**
     * 事务提交之后才进行操作(提交成功)
     *
     * @param runnable
     */
    public static void afterCommit(Runnable runnable) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                runnable.run();
            }
        });
    }

    /**
     * 事务提交之后才进行操作(包括提交以及回滚)
     *
     * @param runnable
     */
    public static void afterCompletion(Runnable runnable) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                runnable.run();
            }
        });
    }
}