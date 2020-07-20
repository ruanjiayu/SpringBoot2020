package com.fun.uncle.springboot2020.mapper;

import com.fun.uncle.springboot2020.domain.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/7/20 5:32 下午
 * @Version: 0.0.1-SNAPSHOT
 */
@Repository
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    /**
     * 查询所有的用户
     * @return
     */
    List<UserInfo> findAll();
}