package com.fun.uncle.springboot2020.dto;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    /**
     * 题图地址
     */
    private String imgUrl;

    /**
     * 题的框
     */
    private List<List<PositionDTO>> posList;
}