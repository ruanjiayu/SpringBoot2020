package com.fun.uncle.springboot2020.service.db2;

import com.fun.uncle.springboot2020.domain.UserInfo;

import java.util.List;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/7/20 5:48 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public interface Db2UserInfoService {

    /**
     * 查询所有的用户
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 插入一条记录
     * @param userInfo
     * @return
     */
    int append(UserInfo userInfo);

    /**
     * 通过用户昵称
     * @param username
     * @return
     */
    UserInfo getByUsername(String username);

}
