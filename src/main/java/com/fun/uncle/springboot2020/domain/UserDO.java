package com.fun.uncle.springboot2020.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description: 用户表
 * @Author: Summer
 * @DateTime: 2020/7/20 3:59 下午
 * @Version: 0.0.1-SNAPSHOT
 */
@Data
@Entity
@Table(name = "user_info", schema = "springboot")
public class UserDO {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer age;

    /**
     * 不建议使用JPA来创建表
     */
    @Column(name = "address", columnDefinition = "varchar(32) comment '地址'")
    private String address;

    private LocalDate birthday;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
