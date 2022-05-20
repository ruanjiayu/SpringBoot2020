package com.fun.uncle.springboot2020.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 版本工具类
 * @Author: Summer
 * @DateTime: 2021/11/15 10:48 上午
 * @Version: 0.0.1-SNAPSHOT
 */
public class VersionUtil {


    private static final String DOT = "\\.";


    /**
     * 版本判断
     * @param curVersion 当前版本
     * @param minVersion 最小版本
     * @return
     */
    public static boolean isSupportVersion(String curVersion, String minVersion) {
        if (StringUtils.isAnyBlank(minVersion, curVersion)) {
            return true;
        }
        String[] curVersionList = curVersion.split(DOT);
        String[] minVersionList = minVersion.split(DOT);
        if (curVersionList.length < 3 || minVersionList.length < 3) {
            return true;
        }

        for (int i = 0; i < 3; i++) {
            Integer cur = Integer.parseInt(curVersionList[i]);
            Integer min = Integer.parseInt(minVersionList[i]);
            // 相等则比较下一组值
            if (cur > min) {
                return true;
            } else if (cur < min) {
                return false;
            }
        }
        return true;
    }

    /**
     * 版本号比较
     * @param v1
     * @param v2
     * @return 0代表相等，1代表左边大，-1代表右边大
     * compareVersion("1.0.358_20180820090554","1.0.358_20180820090553")=1
     * compareVersion("1.0.360","1.0.358_20180820090553")=1
     * compareVersion("1.0.358.2","1.0.358_20180820090553")=-1
     * compareVersion("1.0.0.2","1.0.1")=-1
     */
    public static int compareVersion(String v1, String v2) {
        if (v1.equals(v2)) {
            return 0;
        }
        String[] version1Array = v1.split("[._]");
        String[] version2Array = v2.split("[._]");
        int index = 0;
        int minLen = Math.min(version1Array.length, version2Array.length);
        long diff = 0;

        while (index < minLen
                && (diff = Long.parseLong(version1Array[index])
                - Long.parseLong(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            for (int i = index; i < version1Array.length; i++) {
                if (Long.parseLong(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Long.parseLong(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }


    public static void main(String[] args) {
        System.out.println(compareVersion("1.2.4", "1.3.5"));
    }
}
