package com.fun.uncle.springboot2020.service.impl;

import com.fun.uncle.springboot2020.cache.CacheKey;
import com.fun.uncle.springboot2020.config.redis.RedisCacheManager;
import com.fun.uncle.springboot2020.domain.UserInfo;
import com.fun.uncle.springboot2020.mapper.UserInfoMapper;
import com.fun.uncle.springboot2020.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/7/20 5:48 下午
 * @Version: 0.0.1-SNAPSHOT
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Override
    public List<UserInfo> findAll() {
        String redisKey = CacheKey.USER_INFO_ALL_DB1;
        Supplier<List<UserInfo>> supplier = () -> userInfoMapper.findAll();
        return redisCacheManager.assembleRedisList(supplier, UserInfo.class, redisKey, 180L);
    }


    @Override
    public int append(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }


    @Override
    public UserInfo getByUsername(String username) {
        return userInfoMapper.getByUsername(username);
    }
}
