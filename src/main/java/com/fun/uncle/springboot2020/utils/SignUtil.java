package com.fun.uncle.springboot2020.utils;

import com.google.common.collect.Lists;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2021/6/2 3:40 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class SignUtil {

    private static Logger error = LoggerUtil.COMMON_ERROR;

    /**
     * 构建对应的签名
     * @param params
     * @param signName
     * @param signKey
     * @return
     */
    public static String createSign(Map<String, String> params, String signName, String signKey) {
        SortedMap<String, String> sortedMap = new TreeMap<>(params);
        StringBuilder toSign = new StringBuilder();
        for (String key : sortedMap.keySet()) {
            String value = params.get(key);
            boolean shouldSign = false;
            if (StringUtils.isNotEmpty(value) && !Lists.newArrayList("sign", "key", "xmlString", "xmlDoc", "couponList").contains(key)) {
                shouldSign = true;
            }

            if (shouldSign) {
                toSign.append(key).append("=").append(value).append("&");
            }
        }
        toSign.append(signName).append("=").append(signKey);
        return DigestUtils.md5Hex(toSign.toString()).toUpperCase();
    }

    /**
     * 将Object转换成Map。注意会将value为null的key舍弃
     *
     * @param obj 对象
     * @return 返回Map
     * @throws IllegalAccessException
     */
    public static HashMap<String, String> getObjectToMap(Object obj) {
        HashMap<String, String> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        try {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                if (value == null) {
                    continue;
                }
                map.put(fieldName, value.toString());
            }
            return map;
        } catch (IllegalAccessException e) {
            LoggerUtil.error(error, e, "对象转map出现异常，请留意");
            return null;
        }
    }
}
