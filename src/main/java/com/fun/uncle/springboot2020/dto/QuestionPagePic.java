package com.fun.uncle.springboot2020.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionPagePic {

    /**
     * 整张卷面图的地址
     */
    private String imgUrl;

    private int orgHeight;

    private int orgWidth;

    /**
     * ai切题后的题目列表
     */
    private List<Question> questionList;
}