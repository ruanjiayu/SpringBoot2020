package com.fun.uncle.springboot2020.controller;

import com.fun.uncle.springboot2020.annotation.Validate;
import com.fun.uncle.springboot2020.request.UserRequest;
import com.fun.uncle.springboot2020.request.UserRequest2;
import com.fun.uncle.springboot2020.utils.LoggerUtil;
import com.fun.uncle.springboot2020.vo.CommonResult;
import com.fun.uncle.springboot2020.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
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

    private final static Logger logger = LoggerUtil.COMMON_INFO;

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
    public CommonResult<UserVO> requestBody(@RequestBody @Validated UserRequest userRequest) {
        LoggerUtil.info(logger, userRequest);
        UserVO userVO = new UserVO();
        userVO.setId(1L);
        userVO.setNickname("RequestBody");

        return CommonResult.sucess(userVO);
    }



    @ApiOperation(value = "自己定义的参数校验")
    @PostMapping("/selfValidate")
    public CommonResult<UserVO> selfValidate(@RequestBody @Validate UserRequest2 userRequest) {
        LoggerUtil.info(logger, userRequest);
        UserVO userVO = new UserVO();
        userVO.setId(1L);
        userVO.setNickname("自己定义的参数校验");

        return CommonResult.sucess(userVO);
    }

}
