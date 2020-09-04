package com.fun.uncle.springboot2020.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Description: 进行URL的编码操作
 * @Author: Summer
 * @DateTime: 2020/9/4 4:00 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class CodeUtil {

    public static String encode(String url) {
        try {
            url = URLEncoder.encode(url, "UTF-8");
            return url;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return url;
        }
    }

    public static String encodeByGBK(String value) {
        try {
            value = URLEncoder.encode(value, "GBK");
            return value;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return value;
        }
    }

    public static String decode(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        try {
            url = URLDecoder.decode(url, "UTF-8");
            return url;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return url;
        }
    }

}
