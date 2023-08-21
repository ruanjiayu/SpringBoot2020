package com.fun.uncle.springboot2020.controller;

import com.fun.uncle.springboot2020.domain.UserInfo;
import com.fun.uncle.springboot2020.service.UserInfoService;
import com.fun.uncle.springboot2020.service.db2.Db2UserInfoService;
import com.fun.uncle.springboot2020.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description: 用户控制层
 * @Author: Summer
 * @DateTime: 2020/7/20 5:34 下午
 * @Version: 0.0.1-SNAPSHOT
 */
@Api(tags = "用户控制层")
@RestController
@RequestMapping(value = "userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private Db2UserInfoService db2UserInfoService;

    @ApiOperation(value = "查询所有用户")
    @GetMapping(value = "findDb1")
    public List<UserVO> findDb1() {
        List<UserInfo> userInfos = userInfoService.findAll();
        List<UserVO> results = userInfos.stream().map(userInfo -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userInfo, userVO);
            return userVO;
        }).collect(Collectors.toList());
        return results;
    }


    @ApiOperation(value = "查询所有用户在数据库二")
    @GetMapping(value = "findDb2")
    public List<UserVO> findDb2() {
        List<UserInfo> userInfos = db2UserInfoService.findAll();
        List<UserVO> results = userInfos.stream().map(userInfo -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userInfo, userVO);
            return userVO;
        }).collect(Collectors.toList());
        return results;
    }


    @ApiOperation(value = "插入用户")
    @PostMapping(value = "append")
    public String append(@RequestBody UserInfo userInfo) {
        int success = userInfoService.append(userInfo);
        return Objects.equals(success, 1) ? "插入成功" : "插入失败";
    }
}
