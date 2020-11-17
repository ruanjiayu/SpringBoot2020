package com.fun.uncle.springboot2020.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 身份证验证
 * <p>
 * 判断18位身份证的合法性
 * </p>
 * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
 * <p>
 * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
 * </p>
 * <p>
 * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码； 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码； 6.第17位数字表示性别：奇数表示男性，偶数表示女性； 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
 * </p>
 * <p>
 * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
 * </p>
 * <p>
 * 2.将这17位数字和系数相乘的结果相加。
 * </p>
 * <p>
 * 3.用加出来和除以11，看余数是多少？
 * </p>
 * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。
 * <p>
 * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
 * </p>
 */
public class IdCardValidatorUtil {

    private final static Logger biz_log = LoggerFactory.getLogger("biz_log");

    /**
     * 每位加权因子
     */
    private final static int[] power = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * 验证所有的身份证的合法性
     *
     * @param idCard
     * @return true 合法、false 失败
     */
    public static boolean isIdentityCard(String idCard) {
        if (idCard.length() == 15) {
            idCard = convertIdCardBy15bit(idCard);
        }
        return isValidate18IdCard(idCard);
    }

    /**
     * 判断18位身份证的合法性
     *
     * @param idCard
     * @return
     */
    private static boolean isValidate18IdCard(String idCard) {
        // 非18位为假
        if (StringUtils.length(idCard) == 18) {
            // 获取前17位
            String idCard17 = idCard.substring(0, 17);
            // 获取第18位
            String idCard18Code = idCard.substring(17, 18);
            // 是否都为数字
            if (isDigital(idCard17)) {
                char[] c = idCard17.toCharArray();
                int[] bit = convertCharToInt(c);
                int sum17 = getPowerSum(bit);
                // 将和值与11取模得到余数进行校验码判断
                String checkCode = getCheckCodeBySum(sum17);
                // 将身份证的第18位与算出来的校码进行匹配，不相等就为假
                return idCard18Code.equalsIgnoreCase(checkCode);
            }
        }
        return false;
    }

    /**
     * 将15位的身份证转成18位身份证
     *
     * @param idCard
     * @return 18
     */
    private static String convertIdCardBy15bit(String idCard) {
        // 非15位身份证
        if (StringUtils.length(idCard) != 15 && !isDigital(idCard)) {
            return null;
        }
        // 获取出生年月日
        String birthday = idCard.substring(6, 12);
        Date birthDate = null;
        try {
            birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
        } catch (ParseException e) {
            biz_log.error("convertIdCardBy15bit", e);
//            logger.error(logger, e, "convertIdCardBy15bit");
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String idCard17 = idCard.substring(0, 6) + year + idCard.substring(8);
        char[] c = idCard17.toCharArray();
        // 将字符数组转为整型数组
        int[] bit = convertCharToInt(c);
        int sum17 = getPowerSum(bit);
        // 获取和值与11取模得到余数进行校验码
        String checkCode = getCheckCodeBySum(sum17);
        // 获取不到校验位
        if (null != checkCode) {
            // 将前17位与第18位校验码拼接
            idCard17 += checkCode;
        }
        return idCard17;

    }

    /**
     * 数字验证
     *
     * @param str
     * @return
     */
    private static boolean isDigital(String str) {
        return !StringUtils.isBlank(str) && str.matches("^[0-9]*$");
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param bit
     * @return 返回 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     */
    private static int getPowerSum(int[] bit) {
        int sum = 0;
        if (power.length != bit.length) {
            return sum;
        }
        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断 // 第18位校检码
     *
     * @param sum17
     * @return 校验位
     */
    private static String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
        }
        return checkCode;
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param c
     * @return
     * @throws NumberFormatException
     */
    private static int[] convertCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }

    /**
     * https://mp.weixin.qq.com/s/xe1am_aJ_thIRE8yzGcRIQ
     * 验证香港身份证号码(存在Bug，部份特殊身份证无法检查)
     * <p>
     * 身份证前2位为英文字符，如果只出现一个英文字符则表示第一位是空格，对应数字58 前2位英文字符A-Z分别对应数字10-35 最后一位校验码为0-9的数字加上字符"A"，"A"代表10
     * </p>
     * <p>
     * 将身份证号码全部转换为数字，分别对应乘9-1相加的总和，整除11则证件号码有效
     * </p>
     *
     * @param idCard 身份证号码
     * @return 验证码是否符合
     */
    public static boolean validateHkCard(String idCard) {
        try {
            if (StringUtils.isEmpty(idCard)) {
                return false;
            }
            String card = idCard.replaceAll("[(|)|（|）]", "");
            card = card.toUpperCase();
            if (card.length() != 8) {
                return false;
            }
            // 开始计算
            int sum = 0;
            char[] cardChar = card.toCharArray();
            for (int i = 0; i < cardChar.length; i++) {
                int num = cardChar[i];
                // 第一位 英文字母转为数值的规律:  A=1, B=2, C=3, ......Y=25, Z=26
                if (i == 0) {
                    num = num - 64;
                }
                // 最后一位, 十六进制, 1,2...9,A,B,C,D,E
                else if (i == 7) {
                    if (num <= 57) {
                        num = num - 48;
                    } else {
                        num = num - 55;
                    }
                } else {
                    num = num - 48;
                }

                int j = cardChar.length - i;
                sum += j * num;
            }
            return sum % 11 == 0;
        } catch (Exception e) {
            return false;
        }
    }
}
