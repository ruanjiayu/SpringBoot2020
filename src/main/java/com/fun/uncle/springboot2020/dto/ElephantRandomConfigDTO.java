package com.fun.uncle.springboot2020.dto;

import com.fun.uncle.springboot2020.dto.base.RandomConfigDTO;

/**
 * @Description: 金象随机奖励配置
 * @Author: fan
 * @DateTime: 2020/10/14 12:07 上午
 * @Version: 0.0.1-SNAPSHOT
 */
public class ElephantRandomConfigDTO extends RandomConfigDTO {

    /**
     * 类型
     */
    private Integer type;

    /**
     * 奖励
     */
    private Long reward;

    /**
     * 描述
     */
    private String desc;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getReward() {
        return reward;
    }

    public void setReward(Long reward) {
        this.reward = reward;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
