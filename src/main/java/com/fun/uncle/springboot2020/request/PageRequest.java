package com.fun.uncle.springboot2020.request;

import java.util.Objects;
import java.util.Optional;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2022/8/11 13:41
 * @Version: 1.0.0
 */
public class PageRequest {


    /**
     * 每页默认显示的记录数大小: 15条
     */
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 每页默认显示的记录数大小: 10条
     */
    private static final Integer MAX_PAGE_SIZE = 1000;

    /**
     * 每页显示的记录条数
     */
    protected Integer size = DEFAULT_PAGE_SIZE;

    /**
     * 当前页数
     */
    protected Integer page = 0;

    /**
     * 前一页的最后一条记录id,如果没有可以不传
     */
    protected Long lastId;


    public PageRequest() {
    }

    public PageRequest(Integer page, Integer size) {
        this.size = size;
        this.page = page;
    }


    /**
     * 对应的行数
     *
     * @return
     */
    public Integer getStartRow() {
        if (page < 0) {
            page = 0;
        }
        // 使用了lastId就不能使用startRow
        return Objects.nonNull(lastId) && lastId > 0 ? 0 : (page * size);
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = Optional.ofNullable(size)
                .filter(pageSize -> pageSize <= MAX_PAGE_SIZE)
                .orElse(MAX_PAGE_SIZE);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = Optional.ofNullable(page).orElse(1);
    }

    public Long getLastId() {
        return lastId;
    }

    public void setLastId(Long lastId) {
        if (Objects.nonNull(lastId) && lastId > 0) {
            this.lastId = lastId;
        }
    }


}
