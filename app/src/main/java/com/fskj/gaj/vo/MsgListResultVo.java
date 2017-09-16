package com.fskj.gaj.vo;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class MsgListResultVo {
    /**
     * "mid": "记录id",
     "title": "标题",
     "createtime": "创建时间",
     "visit": 258,    //访问量
     "top": "1"     //1=置顶

     */
    private String mid;
    private String title;
    private String createtime;
    private String top;
    private int visit;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }
}
