package com.fun.uncle.springboot2020.dto.base;

/**
 * @Description: 随机配置
 * @Author: Summer
 * @DateTime: 2020/10/13 10:20 上午
 * @Version: 0.0.1-SNAPSHOT
 */
public class RandomConfigDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 库存:-1代表无库存限制
     */
    private Integer stock;

    /**
     * 权重
     */
    private Integer weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
