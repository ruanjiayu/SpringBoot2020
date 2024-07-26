package com.fun.uncle.springboot2020.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2024/2/26 17:21
 * @Version: 1.0.0
 */
@Data
@AllArgsConstructor
public class HelloRequest implements AesParams {

    private Long id;

    private String nickname;

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
}
