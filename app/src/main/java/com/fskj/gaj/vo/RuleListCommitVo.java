package com.fskj.gaj.vo;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class RuleListCommitVo {
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return String.format("date=%s",getDate());
    }
}
