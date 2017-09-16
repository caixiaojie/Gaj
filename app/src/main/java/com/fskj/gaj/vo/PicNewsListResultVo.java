package com.fskj.gaj.vo;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class PicNewsListResultVo {
    /**
     * "pid": "记录id",
     "title": "标题",
     "createtime": "创建时间",
     "imgurl": "图片相对路径",
     "visit": 258,    //访问量
     "top": "1"     //1=置顶
     */
    private String pid;
    private String title;
    private String createtime;
    private String imgurl;
    private String top;
    private int visit;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
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
