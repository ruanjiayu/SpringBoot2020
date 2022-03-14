package com.fun.uncle.springboot2020.controller;

import com.fun.uncle.springboot2020.request.UserRequest;
import com.fun.uncle.springboot2020.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/7/20 11:53 上午
 * @Version: 0.0.1-SNAPSHOT
 */
@Api(tags = "调用参数")
@RestController
public class ParamController {


    @ApiOperation("无参构造方法")
    @GetMapping(value = "/noAnnotation")
    public UserRequest noAnnotation(UserRequest userRequest) {
        return userRequest;
    }


    @ApiOperation("@RequestParam方式")
    @GetMapping(value = "/requestParam")
    public UserRequest requestParam(@RequestParam String name, @RequestParam int age) {
        UserRequest userRequest = new UserRequest();
        userRequest.setName(name);
        userRequest.setAge(age);
        return userRequest;
    }


    @ApiOperation(value = "@PathVariable方式")
    @GetMapping("/pathVariable/{nickname}/{password}")
    public UserVO PathVariable(@PathVariable String nickname, @PathVariable String password) {
        UserVO user = new UserVO();
        user.setNickname(nickname);
        return user;
    }


    @ApiOperation(value = "@RequestBody")
    @PostMapping("/requestBody")
    public UserVO requestBody(@RequestBody @Validated UserRequest userRequest) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userRequest, userVO);
        return userVO;
    }





}
