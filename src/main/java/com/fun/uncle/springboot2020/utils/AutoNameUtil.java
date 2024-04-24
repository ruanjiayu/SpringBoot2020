package com.fun.uncle.springboot2020.utils;

import java.util.Random;

/**
 * 随机生成汉字或随机生成中文姓名
 */
public abstract class AutoNameUtil {

    static {
        singleSur = new String[]{"赵",
                "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许", "何",
                "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云",
                "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆",
                "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐",
                "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "姚",
                "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊",
                "纪", "舒", "屈", "项", "祝", "董", "梁"
        };

        chineseWord = new String[]{
                "浩", "固", "之", "亮", "政", "谦", "亨", "奇", "轮", "翰", "朗", "伯", "宏", "贵", "国", "鹏", "泽", "胜", "龙", "元", "全", "学", "祥", "才", "发", "武",
                "邦", "望", "尧", "珺", "然", "涵", "亦", "丞", "博", "淼", "琪", "群", "驰", "照", "传", "诗", "至", "充", "靖", "会", "力", "大", "山", "之", "中", "方",
                "序", "宜", "世", "梓", "竹", "舜", "丞", "怡", "骁", "俊", "沣", "骅", "歌", "伟", "刚", "俊", "峰", "强", "军", "平", "保", "东", "信", "子", "文", "辉",
                "光", "天", "达", "中", "思", "群", "茂", "进", "林", "有", "坚", "和", "彪", "明", "永", "志", "义", "健", "世", "广", "兴", "良", "厚", "庆", "海", "波",
                "绍", "功", "山", "仁", "松", "善", "磊", "民", "友", "裕", "树", "炎", "江", "栋", "维", "德", "行", "启", "元", "全", "克", "伦", "晨", "辰", "士", "以",
                "固", "之", "轮", "良", "海", "朗", "伯", "言", "若", "鸣", "朋", "斌", "梁", "熙", "运", "阿", "迎", "乾", "逊", "嶂", "黎", "程", "白", "左", "宜", "世",
                "嶙", "崞", "陆", "科", "迪", "弘", "有", "邵", "凯", "稳", "岷", "舜", "丞", "陆", "达", "丰", "崇", "睿", "峻", "崎", "峭", "峰", "嵃", "秦", "凡", "中",
                "彪", "先", "民", "友", "裕", "翔", "旭", "河", "亮", "政", "谦", "亨", "奇", "发", "武", "新", "树", "炎", "利", "彬", "富", "顺", "信", "子", "杰", "涛",
                "有", "中", "清", "飞", "坚", "军", "泰", "平", "和", "敬", "振", "会", "思", "畅", "与", "圣", "铭", "溓", "滔", "溪", "巩", "影", "锐", "展", "仁", "世",
                "娴", "巧", "秀", "娟", "英", "华", "美", "黛", "芸", "娜", "静", "珠", "婷", "雅", "淑", "薇", "芝", "玉", "娅", "玲", "芬", "芳", "梦", "彩", "佳", "妍",
                "琼", "艺", "柔", "卿", "聪", "澜", "纯", "毓", "悦", "媛", "冰", "茜", "颖", "雪", "茗", "羽", "希", "宁", "欣", "滢", "馥", "璧", "璐", "影", "荔", "筠",
                "可", "兰", "凤", "洁", "梅", "琳", "素", "云", "莲", "真", "环", "爽", "菊", "霞", "香", "妹", "惠", "倩", "青", "月", "萍", "红", "莺", "嘉", "园", "勤",
                "珍", "露", "瑶", "爱", "燕", "贞", "莉", "桂", "娣", "翠", "叶", "琦", "春", "昭", "秋", "瑞", "凡", "锦", "琬", "珊", "艳", "莎", "竹", "霭", "瑾", "咏",
                "怡", "婵", "姣", "婉", "雁", "蓓", "飘", "育", "纨", "仪", "蓉", "眉", "君", "琴", "荷", "丹", "蕊", "娥", "菁", "婕", "琰", "韵", "融", "馨", "瑗", "宜",
                "凝", "晓", "欢", "霄", "枫", "慧", "荣", "岚", "晶", "苑", "菲", "寒", "伊"
        };
    }

    /**
     * 单姓：953
     */
    private static final String[] singleSur;

    /**
     * 3502个汉字
     */
    private static final String[] chineseWord;

    private static final Random random = new Random();

    /**
     * 单字名占比
     */
    public static final int singlePercentage = 40;
    /**
     * 双字名占比
     */
    public static final int doublePercentage = 42;
    /**
     * 三字名占比
     */
    public static final int triplePercentage = 8;

    /**
     * 获取单姓总个数
     */
    public static final int getSingleSurNum() {
        return singleSur.length;
    }

    /**
     * 根据下标获取单姓氏
     */
    public static final String getSingleSur(int index) {
        return singleSur[index];
    }


    /**
     * 获取少汉字总个数
     */
    public static final int getChineseWordNum() {
        return chineseWord.length;
    }

    /**
     * 根据下标获取汉字
     */
    public static final String getChineseWord(int index) {
        return chineseWord[index];
    }

    /**
     * 随机生成姓
     * 按单姓","复姓和少数民族姓的个数在总数中的比例为概率值生成对应姓氏
     */
    public static final String autoSur() {
        // 姓氏总数
        int totalSurNum = getSingleSurNum();
        // 姓氏下标
        int autoNum = random.nextInt(totalSurNum);

        return getSingleSur(autoNum);
    }

    /**
     * 获取名的字数
     */
    public static final int getNameNum() {
        int totalNum = singlePercentage + doublePercentage + triplePercentage;
        int autoNum = random.nextInt(totalNum);
        if (autoNum > singlePercentage - 1 && autoNum < singlePercentage + doublePercentage) {
            return 2;// 二字名
        } else {
            return 1;// 单字名
        }
    }

    /**
     * 随机生成名
     */
    public static final String autoName() {
        int nameNum = getNameNum();// 获取名的字数
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nameNum; i++) {
            int index = random.nextInt(chineseWord.length);
            sb.append(chineseWord[index]);
        }
        return sb.toString();
    }

    /**
     * 随机生成姓名
     */
    public static final String autoSurAndName() {
        return autoSur() + autoName();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(autoSurAndName());
        }
    }
}


