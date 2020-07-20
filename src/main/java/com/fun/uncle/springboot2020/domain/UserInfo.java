package com.fun.uncle.springboot2020.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

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
    private Integer id;

    @ApiModelProperty(value="名字")
    private String name;

    @ApiModelProperty(value="地址")
    private String address;

    @ApiModelProperty(value="年纪")
    private Integer age;

    @ApiModelProperty(value="生日")
    private LocalDate birthday;

    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value="更新时间")
    private LocalDateTime updateTime;
}