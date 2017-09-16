package com.fskj.gaj.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class AddRuleCommitVo {
    /**
     * rid		会议室id
     rname		会议室名称
     name		申请人姓名
     phone		申请人电话
     date		申请日期
     title		会议名称
     peole		参会人员，可空串
     afternoon	“1“=下午，否则上午

     */

    private String rid;
    private String rname;
    private String name;
    private String phone;
    private String date;
    private String title;
    private String peole;
    private String afternoon;

    public Map<String,String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("rid", getRid());
        map.put("rname",getRname());
        map.put("name",getName());
        map.put("phone",getPhone());
        map.put("date",getDate());
        map.put("title",getTitle());
        map.put("peole",getPeole());
        map.put("afternoon",getAfternoon());
        return map;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPeole() {
        return peole;
    }

    public void setPeole(String peole) {
        this.peole = peole;
    }

    public String getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(String afternoon) {
        this.afternoon = afternoon;
    }
}
