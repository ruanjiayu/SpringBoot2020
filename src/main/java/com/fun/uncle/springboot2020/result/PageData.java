package com.fun.uncle.springboot2020.result;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description: 数据返回
 * @Author: summer
 * @CreateDate: 2022/8/11 13:41
 * @Version: 1.0.0
 */
public class PageData<T> {

    /**
     * 列表
     */
    private List<T> list;

    /**
     * 是否为最后一页
     */
    private boolean endPage;


    public PageData() {
    }

    public PageData(List<T> list, boolean endPage) {
        this.list = list;
        this.endPage = endPage;
    }

    public PageData(List<T> list, int pageSize) {
        this.list = list;
        if (CollectionUtils.isEmpty(list)) {
            this.endPage = true;
        } else {
            this.endPage = list.size() < pageSize;
        }

    }

    public void buildData(List<T> list, int pageSize) {
        this.list = list;
        if (CollectionUtils.isEmpty(list)) {
            this.endPage = true;
        } else {
            this.endPage = list.size() < pageSize;
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isEndPage() {
        return endPage;
    }

    public void setEndPage(boolean endPage) {
        this.endPage = endPage;
    }
}
