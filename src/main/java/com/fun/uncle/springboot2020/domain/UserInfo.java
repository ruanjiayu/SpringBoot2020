package com.fun.uncle.springboot2020.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*
* @Description:
* @Author: Summer
* @DateTime: 2020/7/20 5:32 下午
* @Version: 0.0.1-SNAPSHOT
*/

@ApiModel(value="用户信息表")
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 4546587735196981772L;

    @ApiModelProperty(value="ID")
    private Long id;

    private String nickname;

    @ApiModelProperty(value="名字")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    private Integer status;

    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value="更新时间")
    private LocalDateTime updateTime;
}