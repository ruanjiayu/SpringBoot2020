package com.fun.uncle.springboot2020.dto;

import com.fun.uncle.springboot2020.dto.base.RandomConfigDTO;

/**
 * @Description: 话费充值的随机配置
 * @Author: fan
 * @DateTime: 2020/10/14 12:08 上午
 * @Version: 0.0.1-SNAPSHOT
 */
public class PhoneBillRandomConfigDTO extends RandomConfigDTO {

    /**
     * 折扣-百分比
     */
    private Integer discount;

    /**
     * 到账金额
     */
    private Integer arrivalAmount;

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getArrivalAmount() {
        return arrivalAmount;
    }

    public void setArrivalAmount(Integer arrivalAmount) {
        this.arrivalAmount = arrivalAmount;
    }
}
