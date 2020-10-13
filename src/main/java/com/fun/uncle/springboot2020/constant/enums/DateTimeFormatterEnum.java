package com.fun.uncle.springboot2020.constant.enums;

import java.time.format.DateTimeFormatter;

/**
 * @Description: 时间格式转换枚举类
 * @Author: Summer
 * @DateTime: 2020/10/13 10:41 上午
 * @Version: 0.0.1-SNAPSHOT
 */
public enum DateTimeFormatterEnum {

    FORMAT_YM(1, "yyyyMM", DateTimeFormatter.ofPattern("yyyyMM")),

    FORMAT_Y_M(2, "yyyy-MM", DateTimeFormatter.ofPattern("yyyy-MM")),

    FORMAT_YMD(3, "yyyyMMdd", DateTimeFormatter.ofPattern("yyyyMMdd")),

    FORMAT_Y_M_D(4, "yyyy-MM-dd", DateTimeFormatter.ofPattern("yyyy-MM-dd")),

    FORMAT_YMD_HMS(5, "yyyy-MM-dd HH:mm:ss", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),

    FORMAT_YMDHMS(6, "yyyyMMddHHmmss", DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),

    FORMAT_YMDTHMS(7, "yyyy-MM-dd'T'HH:mm:ss", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),

    FORMAT_YMD_HM(8, "yyyy-MM-dd HH:mm", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),

    FORMAT_DHMS(9, "ddHHmmss", DateTimeFormatter.ofPattern("ddHHmmss")),

    FORMAT_Y_M_D_P(10, "yyyy.MM.dd", DateTimeFormatter.ofPattern("yyyy.MM.dd")),

    FORMAT_MD_HM(11, "MM-dd HH:mm", DateTimeFormatter.ofPattern("MM-dd HH:mm")),

    FORMAT_HM(12, "HH:mm", DateTimeFormatter.ofPattern("HH:mm")),

    FORMAT_MDHMS(13, "MMddHHmmss", DateTimeFormatter.ofPattern("MMddHHmmss")),

    FORMAT_Y_M_D_H(14, "yyyy-MM-dd HH", DateTimeFormatter.ofPattern("yyyy-MM-dd HH")),

    FORMAT_YY_MM_DD(15, "yyyy年MM月dd日", DateTimeFormatter.ofPattern("yyyy年MM月dd日")),

    FORMAT_MM_DD(16, "MM月dd日", DateTimeFormatter.ofPattern("MM月dd日"));

    /**
     * id
     */
    private Integer type;

    /**
     * 描述
     */
    private String desc;

    /**
     * 格式
     */
    private DateTimeFormatter formatter;

    DateTimeFormatterEnum(Integer type, String desc, DateTimeFormatter formatter) {
        this.type = type;
        this.desc = desc;
        this.formatter = formatter;
    }

    /**
     * 找不到类型则返回[yyyy-MM-dd HH:mm:ss]
     *
     * @param type
     * @return
     */
    public static DateTimeFormatterEnum getByType(Integer type) {
        for (DateTimeFormatterEnum e : values()) {
            if (e.type.equals(type)) {
                return e;
            }
        }
        return FORMAT_YMD_HMS;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }
}
