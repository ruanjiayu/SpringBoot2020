package com.fun.uncle.springboot2020.request;

import com.fun.uncle.springboot2020.annotation.IsDate;
import com.fun.uncle.springboot2020.constant.enums.DateTimeTypeEnum;
import net.sf.oval.constraint.NotNull;


/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2024/7/29 12:02
 * @Version: 1.0.0
 */
public class UserRequest2 {

    @NotNull
    private Long id;

    @IsDate(message = "startTime 格式不正确", dateTimeType = DateTimeTypeEnum.DATETIME, format = "yyyy-MM-dd HH:mm:ss"/*, profiles = {"get"}*/)
    private String startTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
