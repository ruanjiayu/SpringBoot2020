package com.fun.uncle.springboot2020.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
