package com.fun.uncle.springboot2020.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Override
    int insert(UserInfo record);

    /**
     * 查询所有的用户
     *
     * @return
     */
    List<UserInfo> findAll();


    /**
     * 批量插入数据
     *
     * @param records
     * @return
     */
    int batchAdd(List<UserInfo> records);

    /**
     * 通过username来获取信息
     *
     * @param username
     * @return
     */
    UserInfo getByUsername(String username);
}