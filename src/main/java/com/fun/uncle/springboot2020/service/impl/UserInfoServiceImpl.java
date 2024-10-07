package com.fun.uncle.springboot2020.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.uncle.springboot2020.cache.CacheKey;
import com.fun.uncle.springboot2020.config.redis.RedisCacheManager;
import com.fun.uncle.springboot2020.domain.UserInfo;
import com.fun.uncle.springboot2020.mapper.UserInfoMapper;
import com.fun.uncle.springboot2020.service.UserInfoService;
import com.fun.uncle.springboot2020.utils.LoggerUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/7/20 5:48 下午
 * @Version: 0.0.1-SNAPSHOT
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    private final static Logger common_info = LoggerUtil.COMMON_INFO;

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
    @Transactional(rollbackFor = Exception.class)
    public int append(UserInfo userInfo) {
//        return userInfoMapper.insert(userInfo);
        LoggerUtil.info(common_info, "开始", userInfo);
        userInfoMapper.removeByNicknameAndPassword(userInfo.getNickname(), userInfo.getPassword());
        LoggerUtil.info(common_info, "删除后新增", userInfo);
        try {
            Thread.sleep(15000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int i = userInfoMapper.insert(userInfo);
        LoggerUtil.info(common_info, "结束", userInfo);
        return i;

    }

    @Override
    public int batchAdd(List<UserInfo> records) {
//      userInfoMapper.batchAdd(records);
        this.saveBatch(records);
        LoggerUtil.info(common_info, "插入用户数据", records);
        return 1;
    }


    @Override
    public UserInfo getByUsername(String username) {
        return userInfoMapper.getByUsername(username);
    }

}
