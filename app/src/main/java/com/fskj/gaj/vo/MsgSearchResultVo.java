package com.fskj.gaj.vo;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class MsgSearchResultVo {
    /**
     * "mid": "记录id",
     "title": "标题",
     "createtime": "创建时间",
     "visit": 258,
     "type": "分类名称",
     "reason": "0=图片新闻，1=文章  其它=通知"

     */
    private String mid;
    private String title;
    private String createtime;
    private String type;
    private String reason;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }
}
