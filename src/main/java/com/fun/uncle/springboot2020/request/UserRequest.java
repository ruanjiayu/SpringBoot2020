package com.fun.uncle.springboot2020.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/7/20 11:54 上午
 * @Version: 0.0.1-SNAPSHOT
 */
@Data
public class UserRequest {

    @NotEmpty(message = "名字不能为空")
    private String name;

    @Range(min = 1,max = 120,message = "年龄要在1到120之间")
    private int age;

    @Email(message = "Email格式不正确")
    private String email;

    @Past(message = "生日必须为过去的时间")
    private LocalDate birthDay;

    @Future(message = "结婚必须在2022年之后")
    private LocalDate marry;

    @Min(value = 2_0000, message = "月薪必须大于那么多")
    private int salary;

    @Max(value = 3_141, message = "礼物价格不能太高")
    private int giftPrice;

    @Length(min = 5, max = 30, message = "地址必须要符合大小")
    private String address;



}
