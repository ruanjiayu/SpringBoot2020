package com.fun.uncle.springboot2020.cache;

/**
 * @Description: 缓存相关的key
 * @Author: Summer
 * @DateTime: 2020/7/21 5:19 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public interface CacheKey {

    /**
     * 所有用户的信息
     */
    String USER_INFO_ALL_DB1 = "user_info_all_db1";

    /**
     *
     */
    String USER_INFO_ALL_DB2 = "user_info_all_db2";

    /**
     * 测试锁
     */
    String TEST_LOCK = "test_lock";
}
