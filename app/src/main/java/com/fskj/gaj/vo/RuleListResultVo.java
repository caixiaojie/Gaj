package com.fskj.gaj.vo;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class RuleListResultVo {
    /**
     *  "id": "记录id",
     "rid": "会议室id",
     "rname": "会议室名称",
     "date": "日期",
     "name": "申请人姓名",
     "phone": "申请电话",
     "timemin": "8=上午，否则下午",
     "rstate": "0=申请中 1=已通过",
     "createtime": "申请时间",
     "title": "会议名称",
     "people": "参会人员"

     */

    private String id;
    private String rid;
    private String rname;
    private String date;
    private String name;
    private String phone;
    private String timemin;
    private String rstate;
    private String createtime;
    private String title;
    private String people;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTimemin() {
        return timemin;
    }

    public void setTimemin(String timemin) {
        this.timemin = timemin;
    }

    public String getRstate() {
        return rstate;
    }

    public void setRstate(String rstate) {
        this.rstate = rstate;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }
}
