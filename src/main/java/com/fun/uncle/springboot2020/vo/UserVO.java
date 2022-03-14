package com.fun.uncle.springboot2020.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/7/20 1:39 下午
 * @Version: 0.0.1-SNAPSHOT
 */
@Data
@ApiModel(value = "用户显示")
public class UserVO {

    @ApiModelProperty(value="ID")
    private Long id;

    @ApiModelProperty(value="名字")
    private String nickname;
}
