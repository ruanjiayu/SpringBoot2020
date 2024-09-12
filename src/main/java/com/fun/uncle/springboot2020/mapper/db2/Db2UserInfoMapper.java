package com.fun.uncle.springboot2020.mapper.db2;

//import com.baomidou.dynamic.datasource.annotation.DS;
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
//@DS("db2")
public interface Db2UserInfoMapper {

    int insert(UserInfo record);

    /**
     * 查询所有的用户
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 通过username来获取信息
     * @param username
     * @return
     */
    UserInfo getByUsername(String username);
}