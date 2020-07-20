package com.fun.uncle.springboot2020.service.impl;

import com.fun.uncle.springboot2020.domain.UserInfo;
import com.fun.uncle.springboot2020.mapper.UserInfoMapper;
import com.fun.uncle.springboot2020.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.findAll();
    }


    @Override
    @Transactional
    public int append(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }
}
