package com.fun.uncle.springboot2020.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2022/12/9 11:23
 * @Version: 1.0.0
 */
@Data
public class CalResult {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String headPic;

    /**
     * 报告生成时间
     */
    private String timeStr;

    /**
     * 个数
     */
    private Integer num;

    /**
     * 时长(单位毫秒)
     */
    private Integer duration;

    /**
     * 千卡
     */
    private String kCalStr;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getkCalStr() {
        return kCalStr;
    }

    public void setkCalStr(String kCalStr) {
        this.kCalStr = kCalStr;
    }

    public static void main(String[] args) {
        CalResult result = new CalResult();
        result.setNickname("");
        result.setHeadPic("");
        result.setTimeStr("");
        result.setNum(0);
        result.setDuration(0);
        result.setkCalStr("1234");


        System.out.println(JSON.toJSONString(result));
    }

}
