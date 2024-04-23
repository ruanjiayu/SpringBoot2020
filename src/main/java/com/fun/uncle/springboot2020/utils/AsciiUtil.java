package com.fun.uncle.springboot2020.utils;

/**
 * @Description: ASCII工具类
 * @Author: summer
 * @CreateDate: 2024/4/7 15:17
 * @Version: 1.0.0
 */
public class AsciiUtil {


    public static void main(String[] args) {
        String url = "https://publicduoguan.oss-cn-hangzhou.aliyuncs.com/ota/s5/S5_v0150_ota.bin";
        System.out.println(strToASCII(url));
    }



    /**
     * str转ascii
     *
     * @param param
     * @return
     */
    public static String strToASCII(String param) {
        StringBuilder asciiStr = new StringBuilder();

        byte[] asciiArray = param.getBytes();

        // 输出ASCII数组
        for (byte b : asciiArray) {
            String asciiCode = Integer.toHexString(b).toUpperCase();
            asciiStr.append(asciiCode);
        }

        return asciiStr.toString();
    }




}
