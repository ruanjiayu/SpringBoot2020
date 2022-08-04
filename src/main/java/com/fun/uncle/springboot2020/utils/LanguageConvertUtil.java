package com.fun.uncle.springboot2020.utils;

import com.github.promeg.pinyinhelper.Pinyin;

public class LanguageConvertUtil {

    /**
     *  获取汉字的拼音首字母
     * @param chinese  汉字字符串
     * @return
     */
    private static String getFirstLetter(String chinese){
        // 1. 生成拼音
        String pinyin = Pinyin.toPinyin(chinese,"");
        return String.valueOf(pinyin.charAt(0)).toUpperCase();
    }


    public static void main(String[] args) {
        System.out.println(getFirstLetter("李佳裕"));
    }
}
