package com.fun.uncle.springboot2020.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 日志工具类
 * @Author: Summer
 * @DateTime: 2020/11/17 5:32 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class LoggerUtil {

    /**
     * 统一的信息
     */
    public final static Logger COMMON_INFO = LoggerFactory.getLogger("common_info");

    /**
     * 统一的警告
     */
    public final static Logger COMMON_WARN = LoggerFactory.getLogger("common_warn");

    /**
     * 统一的错误
     */
    public final static Logger COMMON_ERROR = LoggerFactory.getLogger("common_error");

    /**
     * 业务日志
     */
    public final static Logger BIZ_LOG = LoggerFactory.getLogger("biz_log");

    /**
     * http请求异常
     */
    public final static Logger HTTP_REMOTE = LoggerFactory.getLogger("http_remote");



    public static void info(Logger logger, Object... args) {
        logger.info(buildString(args));
    }

    public static void warn(Logger logger, Object... args) {
        logger.warn(buildString(args));
    }

    public static void warn(Logger logger, Throwable e, Object... args) {
        logger.warn(buildString(args), e);
    }

    public static void error(Logger logger, Throwable e, Object... args) {
        logger.error(buildString(args), e);
    }

    public static void error(Logger logger, Object... args) {
        logger.error(buildString(args));
    }

    /**
     * 以逗号分隔对象
     *
     * @param args
     * @return
     */
    private static String buildString(Object... args) {
        StringBuilder sb = new StringBuilder();
        // 对象批量输出
        for (int i = 0; i < args.length; i++) {
            // 基本变量与null,String类型
            if (null == args[i] || isPrimitive(args[i])) {
                sb.append(args[i]);
            } else {
                sb.append(JSON.toJSONString(args[i]));
            }
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


    /**
     * 是否为基本数据类型
     *
     * @param target
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static boolean isPrimitive(Object target) {
        Class clzz = target.getClass();
        if (String.class.equals(clzz) || clzz.isPrimitive()) {
            return true;
        }
        return false;
    }
}
