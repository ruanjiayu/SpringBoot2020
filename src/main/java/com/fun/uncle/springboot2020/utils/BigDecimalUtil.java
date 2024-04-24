package com.fun.uncle.springboot2020.utils;

import java.math.BigDecimal;

public class BigDecimalUtil {

    /**
     * double类型的加法运算（需要舍入，保留5位小数，小数位可自定义）
     * @param m1
     * @param m2
     * @return  不加doubleValue()则, 返回BigDecimal对象
     */
    public static double additionDouble(double m1, double m2, int scale) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.add(p2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * double类型的超大数值加法运算（需要舍入，保留5位小数，小数位可自定义）
     * @param m1
     * @param m2
     * @return  不加doubleValue()则, 返回BigDecimal对象
     */
    public static String additionDoubleToStr(double m1, double m2, int scale) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.add(p2).setScale(scale, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * double类型的减法运算（需要舍入，保留5位小数，小数位可自定义）
     * @param m1
     * @param m2
     * @return  不加doubleValue()则, 返回BigDecimal对象
     */
    public static double subtractionDouble(double m1, double m2, int scale) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.subtract(p2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * double类型的超大数值减法运算（需要舍入，保留5位小数，小数位可自定义）
     * @param m1
     * @param m2
     * @return  不加doubleValue()则, 返回BigDecimal对象
     */
    public static String subtractionDoubleToStr(double m1, double m2, int scale) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.subtract(p2).setScale(scale, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * double类型的乘法运算（需要舍入，保留5位小数，小数位可自定义）
     * @param m1
     * @param m2
     * @return  不加doubleValue()则, 返回BigDecimal对象
     */
    public static double multiplicationDouble(double m1, double m2, int scale) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.multiply(p2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * double类型的超大数值的乘法运算（需要舍入，保留5位小数，小数位可自定义）
     * @param m1
     * @param m2
     * @return  不加doubleValue()则, 返回BigDecimal对象
     */
    public static String multiplicationDoubleToStr(double m1, double m2, int scale) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.multiply(p2).setScale(scale, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * double类型的超大数值的乘法运算（需要舍入，保留5位小数，小数位可自定义）
     * @param m1
     * @param m2
     * @return  不加doubleValue()则, 返回BigDecimal对象
     */
    public static String multiplicationDoubleToStr(String m1, String m2, int scale) {
        BigDecimal p1 = new BigDecimal(m1);
        BigDecimal p2 = new BigDecimal(m2);
        return p1.multiply(p2).setScale(scale, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * double类型的除法运算（需要舍入，保留5位小数，小数位可自定义）
     * @param   m1
     * @param   m2
     * @param   scale
     * @return  不加doubleValue()则, 返回BigDecimal对象
     */
    public static double divisionDouble(double m1, double m2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("Parameter error");
        }
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.divide(p2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * double类型的超大数值的除法运算（需要舍入，保留5位小数，小数位可自定义）
     * @param   m1
     * @param   m2
     * @param   scale
     * @return  不加doubleValue()则, 返回BigDecimal对象
     */
    public static String divisionDoubleToStr(double m1, double m2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("Parameter error");
        }
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.divide(p2, scale, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * double类型的保留小数（自定义保留5位小数，至多保留5位小数，舍去后边的值不要，小数位不足5位不用补零）
     * @param m1
     * @return  不加doubleValue()则, 返回BigDecimal对象
     */
    public static double getDoubleKeepDecimals(double m1, int scale) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        double value = p1.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        return value;
    }

    /**
     * double类型的保留小数（自定义保留5位小数，至多保留5位小数，舍去后边的值不要，小数位不足5位不用补零）
     * @param m1
     * @return  不加doubleValue()则, 返回BigDecimal对象
     */
    public static String getDoubleKeepDecimalsToStr(double m1, int scale) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        String value = p1.setScale(scale, BigDecimal.ROUND_HALF_UP).toPlainString();
        return value;
    }
}
