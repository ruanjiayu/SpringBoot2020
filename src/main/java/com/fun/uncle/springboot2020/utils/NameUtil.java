package com.fun.uncle.springboot2020.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 名字工具类(解决名字截取异常)
 * @Author: summer
 * @CreateDate: 2022/9/9 20:57
 * @Version: 1.0.0
 */
public class NameUtil {

    public static void main(String[] args) {
        String substring = "我是🏀⚾️️️";
        String substring2 = "😃😏😓😠";
        System.out.println(nameSub(substring, 5));
    }


    public static String nameSub(String value, int lengthShown) {
        String result;
        if (StringUtils.isBlank(value)) {
            return "";
        }
        if (lengthShown <= 0 || value.length() <= lengthShown) {
            return value;
        }

        try {
            result = value.substring(value.offsetByCodePoints(0, 0),
                    value.offsetByCodePoints(0, lengthShown));
        } catch (Exception e) {
            result = value;
        }

        return result;
    }
}
