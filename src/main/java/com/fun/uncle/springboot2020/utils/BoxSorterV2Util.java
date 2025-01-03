package com.fun.uncle.springboot2020.utils;

import com.alibaba.fastjson.JSON;
import com.fun.uncle.springboot2020.dto.PositionDTO;
import com.fun.uncle.springboot2020.dto.Question;
import com.fun.uncle.springboot2020.dto.QuestionPagePic;

import java.util.List;

/**
 * @Description: 盒子存储工具类
 * @Author: summer
 * @CreateDate: 2024/12/31 09:11
 * @Version: 1.0.0
 */
public class BoxSorterV2Util {

    private final static String str = "{\"imgUrl\":\"https://wb.vod.126.net/courseware/doc/25251963/854538/pf5BC1My/1.jpg\",\"orgHeight\":2336,\"orgWidth\":3305,\"questionList\":[{\"confidence\":0.9999994039535522,\"imgUrl\":\"https://www.wy.com/test/summer/2025/01/02/7273692918482792448QUESTION0_9626b2230e8b4e458d6b3c87f6b868b7.jpg\",\"ocrText\":\"7. [2021·山东德州期中] 若 \\\\( p: (a^2 + 1)x - 4 = 0 \\\\) 是 \\\\( q: x^2 + x - 6 = 0 \\\\) 的充分不必要条件，则 \\\\( a \\\\) 的值为\\nA. 1\\nB. -1\\nC. \\\\(-\\\\frac{\\\\sqrt{3}}{3}\\\\) 或 \\\\(\\\\frac{\\\\sqrt{3}}{3}\\\\)\\nD. 1 或 -1\",\"orgHeight\":229,\"orgWidth\":999,\"posList\":[[{\"x\":197.17898559570312,\"y\":2016.5892333984375},{\"x\":1197.1033935546875,\"y\":2246.434814453125}]],\"questionAnalysis\":{\"category\":\"基础题\",\"subQuestionList\":[{\"info\":4,\"type\":\"选择题\"}]},\"subPicList\":[{\"category\":\"NUMBER\",\"confidence\":0.9965190887451172,\"posList\":[[{\"x\":5.070154666900635,\"y\":5.356698989868164},{\"x\":37.524085998535156,\"y\":36.698883056640625}]]}]}]}";
//    private final static String str = "{\"imgUrl\":\"https://wb.vod.126.net/courseware/doc/25251963/854538/pf5BC1My/1.jpg\",\"orgHeight\":2336,\"orgWidth\":3305}\n";

    public static void main(String[] args) {
        // 创建一个包含框的列表，每个框由两个PositionDTO对象表示

        QuestionPagePic questionPagePic = JSON.parseObject(str, QuestionPagePic.class);

        //

        ase(questionPagePic.getQuestionList());

        // 打印排序后的框列表
        questionPagePic.getQuestionList().forEach(e -> System.out.println(JSON.toJSONString(e)));
    }

    /**
     * 对盒子进行排序操作。
     * 先按比较y坐标、x坐标顺序来进行，值越小，则越靠前
     *
     * @param questionList
     */
    public static void ase(List<Question> questionList) {
        questionList.sort((q1, q2) -> {
            PositionDTO p1 = q1.getPosList().get(0).get(0);
            PositionDTO p2 = q2.getPosList().get(0).get(0);
            int yCompare = Double.compare(p1.getY(), p2.getY());
            if (yCompare != 0) {
                return yCompare;
            }
            return Double.compare(p1.getX(), p2.getX());
        });
    }
}