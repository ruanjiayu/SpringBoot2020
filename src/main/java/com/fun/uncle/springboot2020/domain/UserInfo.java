package com.fun.uncle.springboot2020.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*
* @Description: 完成链式编程
* @Author: Summer
* @DateTime: 2020/7/20 5:32 下午
* @Version: 0.0.1-SNAPSHOT
*/

@ApiModel(value="用户信息表")
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

    public UserInfo(Builder builder) {
        this.id = builder.id;
        this.nickname = builder.nickname;
        this.username = builder.username;
        this.password = builder.password;
        this.status = builder.status;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public static final class Builder{
        private Long id;

        private String nickname;

        private String username;

        private String password;

        private Integer status;

        private LocalDateTime createTime;

        private LocalDateTime updateTime;

        private Builder() {
        }

        public UserInfo build() {
            return new UserInfo(this);
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public void setCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
        }

        public void setUpdateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
        }
    }
}