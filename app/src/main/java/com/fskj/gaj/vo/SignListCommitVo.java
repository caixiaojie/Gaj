package com.fskj.gaj.vo;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class SignListCommitVo {
    private String mid;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return String.format("mid=%s",getMid());
    }
}
