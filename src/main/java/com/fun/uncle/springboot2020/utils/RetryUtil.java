package com.fun.uncle.springboot2020.utils;

import org.slf4j.Logger;
import java.util.function.Supplier;

/**
 * @Description: 重试工具类
 * @Author: Summer
 * @DateTime: 2022/3/14 1:59 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class RetryUtil {

    /**
     * 重试次数
     *
     * @param logger        日志
     * @param supplier      提供者
     * @param msg           msg
     * @param request       请求参数
     * @param maxRetryTimes 重试次数
     * @param <T>
     * @return
     */
    public static <T> T retry(Logger logger, Supplier<T> supplier, String msg, int maxRetryTimes, Object... request) {
        int retryTimes = 1;
        T success = null;
        while (retryTimes < maxRetryTimes + 1) {
            try {
                LoggerUtil.info(logger, msg, "[start]", request);
                success = supplier.get();
                LoggerUtil.info(logger, msg, "[end]", request, "[响应结果为]", success);
                break;
            } catch (Throwable t) {
                if (retryTimes >= maxRetryTimes) {
                    LoggerUtil.error(logger, t, msg, "出现异常超过3次，请注意！！！", request);
                } else {
                    LoggerUtil.warn(logger, t, msg, "出现异常，请注意！！！", request);
                }
            }
            // 按照重试次数后重试
            try {
                Thread.sleep(retryTimes * 100);
            } catch (InterruptedException ignored) {
            }
            retryTimes++;
        }
        Assert.notNull(success, msg + "异常");
        return success;
    }


}
