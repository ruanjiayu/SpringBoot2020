package com.fun.uncle.springboot2020.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/12/23 10:33 上午
 * @Version: 0.0.1-SNAPSHOT
 */
public class NumberUtil {

    private static final BigDecimal TEN_THOUSAND = new BigDecimal(10000);

    public static final BigDecimal HUNDRED = new BigDecimal(100);

    private static final BigDecimal TEN = new BigDecimal(10);

    /**
     * 两个数相乘,保留整数部分,四舍五入
     *
     * @param multiplier
     * @param multiplicand
     * @return
     */
    public static Long multiply(BigDecimal multiplier, BigDecimal multiplicand) {
        BigDecimal result = multiplier.multiply(multiplicand);
        return result.setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
    }

    public static Integer multiplyToInteger(BigDecimal multiplier, BigDecimal multiplicand) {
        BigDecimal result = multiplier.multiply(multiplicand);
        return result.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    public static Long multiplyHundred(BigDecimal multiplier) {
        return multiply(multiplier, HUNDRED);
    }

    public static Long multiplyHundred(String multiplierStr) {
        BigDecimal multiplier = new BigDecimal(multiplierStr);
        return multiply(multiplier, HUNDRED);
    }

    public static Integer multiplyHundredToInteger(String multiplierStr) {
        BigDecimal multiplier = new BigDecimal(multiplierStr);
        return multiplyToInteger(multiplier, HUNDRED);
    }

    public static Integer multiplyHundredToInteger(Double multiplier) {
        return multiplyToInteger(new BigDecimal(multiplier), HUNDRED);
    }

    public static Long multiplyHundred(Double multiplier) {
        return new BigDecimal(multiplier).multiply(HUNDRED).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
    }

    public static Long multiplyHundred(Long multiplierLong) {
        return multiplierLong * 100;
    }

    public static Long multiplyTenThousand(String multiplierStr) {
        BigDecimal multiplier = new BigDecimal(multiplierStr);
        return multiply(multiplier, TEN_THOUSAND);
    }

    public static Float divideHundred(Integer number) {
        BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.divide(HUNDRED, 2, BigDecimal.ROUND_DOWN).floatValue();
    }

    public static Float divideHundred(Long number) {
        BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.divide(HUNDRED, 2, BigDecimal.ROUND_DOWN).floatValue();
    }

    public static Double divideHundredToDouble(Integer number) {
        BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.divide(HUNDRED, 2, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static Long divideHundredToLong(Integer number) {
        BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.divide(HUNDRED, 0, BigDecimal.ROUND_DOWN).longValue();
    }

    public static Long divideHundredToLong(Long number) {
        BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.divide(HUNDRED, 0, BigDecimal.ROUND_DOWN).longValue();
    }

    public static Float format(Float value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static Integer getIntValueByFloat(Float value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        return bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    public static Integer getIntValueByDouble(Double value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        return bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    public static String formatAmount(Long amount) {
        DecimalFormat df = new DecimalFormat("############0.00");
        return df.format(amount / 100F);
    }

    public static String formatAmount(Integer amount) {
        DecimalFormat df = new DecimalFormat("############0.00");
        return df.format(amount / 100F);
    }

    public static String formatAmountWithoutZero(Long amount) {
        DecimalFormat df = new DecimalFormat("#############.##");
        return df.format(amount / 100F);
    }

    public static String formatAmountWithoutZero(Integer amount) {
        DecimalFormat df = new DecimalFormat("#############.##");
        return df.format(amount / 100F);
    }

    // 淘宝比例为百分比，转换为万分比
    public static Integer TBCommRateFormat(String commRate) {
        BigDecimal bigDecimal = new BigDecimal(commRate);
        return bigDecimal.multiply(HUNDRED).intValue();
    }

    // 京东佣金比率为百分比，转换为万分比
    public static Integer JDCommRateFormat(Float commRate) {
        BigDecimal bigDecimal = new BigDecimal(commRate);
        return bigDecimal.multiply(HUNDRED).intValue();
    }

    // 拼多多佣金比率为十万分比，转万分比
    public static Integer PDDCommRateFormat(Long commRate) {
        BigDecimal bigDecimal = new BigDecimal(commRate);
        return bigDecimal.multiply(TEN).intValue();
    }

    /**
     * @param discountPrice
     * @param commRate      万分比
     * @param userCommRate  百分比
     * @param shareRate
     * @return 返回的单位为分
     */
    public static Integer getTotalComm(Integer discountPrice, Integer commRate, Double userCommRate, Integer shareRate) {
        try {
            BigDecimal a = new BigDecimal(discountPrice).divide(HUNDRED, 4, BigDecimal.ROUND_HALF_UP);
            BigDecimal b = new BigDecimal(commRate).divide(HUNDRED, 4, BigDecimal.ROUND_HALF_UP);
            BigDecimal c = new BigDecimal(userCommRate).divide(HUNDRED, 4, BigDecimal.ROUND_HALF_UP);
            if (Objects.nonNull(shareRate)) {
                BigDecimal d = new BigDecimal(shareRate).divide(HUNDRED, 4, BigDecimal.ROUND_HALF_UP);
                return a.multiply(b).multiply(c).multiply(d).setScale(0, BigDecimal.ROUND_DOWN).intValue();
            } else {
                return a.multiply(b).multiply(c).setScale(0, BigDecimal.ROUND_DOWN).intValue();
            }
        } catch (Exception e) {
            return 0;
        }
    }

    public static Integer getTotalComm(Integer discountPrice, Integer commRate, Double userCommRate) {
        return getTotalComm(discountPrice, commRate, userCommRate, null);
    }


    /**
     * 效果预估=付款金额 * (佣金比率+补贴比率) * 分成比率
     * EffectMoney = PayMoney * IncomePercentage * SharedPercentage
     *
     * @param payMoney
     * @param incomePercentage
     * @param sharedPercentage
     * @return
     */
    public static Long getOrderEffectMoney(Long payMoney, Integer incomePercentage, Integer sharedPercentage) {
        BigDecimal effectMoney = new BigDecimal(payMoney);
        effectMoney = effectMoney.multiply(new BigDecimal(incomePercentage)).divide(TEN_THOUSAND, 0, BigDecimal.ROUND_HALF_UP);
        effectMoney = effectMoney.multiply(new BigDecimal(sharedPercentage)).divide(TEN_THOUSAND, 0, BigDecimal.ROUND_HALF_UP);
        return effectMoney.longValue();
    }

    /**
     * 三方平台用户分佣比例
     *
     * @param subsidyRate        与三方平台协定的分佣比
     * @param incomeRate         将协定好比例的几成分给用户
     * @param userCommissionRate 当前用户身份下的分佣比例，需要区分是自营或者普通cps
     * @return 用户实际分佣比例
     */
    public static Double getThirdOrderPercent(Double subsidyRate, Double incomeRate, Double userCommissionRate) {
        BigDecimal a = new BigDecimal(subsidyRate).divide(HUNDRED, 4, BigDecimal.ROUND_HALF_UP);
        BigDecimal b = new BigDecimal(incomeRate).divide(HUNDRED, 4, BigDecimal.ROUND_HALF_UP);
        BigDecimal c = new BigDecimal(userCommissionRate).divide(HUNDRED, 4, BigDecimal.ROUND_HALF_UP);
        return a.multiply(b).multiply(c).doubleValue();
    }

    public static Long getThirdOrderEffectMoney(Long payMoney, Double subsidyRate) {
        BigDecimal effectMoney = new BigDecimal(payMoney);
        effectMoney = effectMoney.multiply(new BigDecimal(subsidyRate));
        return effectMoney.longValue();
    }

    /**
     * 返回商品折扣
     *
     * @param discountPrice 折扣价
     * @param price         商品原价
     * @return
     */
    public static String getDiscount(Integer discountPrice, Integer price) {
        if (Objects.isNull(discountPrice) || Objects.isNull(price) || discountPrice <= 0 || price <= 0) {
            return null;
        }
        Double result = new BigDecimal(discountPrice).divide(new BigDecimal(price), 2, BigDecimal.ROUND_DOWN).multiply(TEN).doubleValue();
        if (result < 10) {
            DecimalFormat df = new DecimalFormat("#.#");
            return df.format(result);
        } else {
            return null;
        }
    }


}
