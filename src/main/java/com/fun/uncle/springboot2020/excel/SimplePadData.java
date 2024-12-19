package com.fun.uncle.springboot2020.excel;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2024/11/27 11:09
 * @Version: 1.0.0
 */
public class SimplePadData {

    /**
     * 记录时间
     */
    private LocalDateTime recordTime;

    /**
     * 写入流量 单位为B
     */
    private Long rx;

    /**
     * 上报流量 单位为B
     */
    private Long tx;

    public LocalDateTime getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(LocalDateTime recordTime) {
        this.recordTime = recordTime;
    }

    public Long getRx() {
        return rx;
    }

    public void setRx(Long rx) {
        this.rx = rx;
    }

    public Long getTx() {
        return tx;
    }

    public void setTx(Long tx) {
        this.tx = tx;
    }
}
