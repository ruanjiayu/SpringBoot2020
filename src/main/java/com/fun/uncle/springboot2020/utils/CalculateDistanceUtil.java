package com.fun.uncle.springboot2020.utils;

/**
 * @Description: 计算距离
 * @Author: Summer
 * @DateTime: 2020/10/13 1:58 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class CalculateDistanceUtil {

    /**
     * 地球半径
     */
    private static final double EARTH_RADIUS = 6378137;

    /**
     * 求的弧度
     * @param d 角度
     * @return
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /** */
    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     *
     * @param lng1 经度
     * @param lat1 纬度
     * @param lng2 经度
     * @param lat2 纬度
     * @return
     */
    public static int getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return (int) s;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        int distance = getDistance(116.434725, 39.918624, 120.065, 30.299);
        System.out.println("Distance is:" + distance);
    }
}
