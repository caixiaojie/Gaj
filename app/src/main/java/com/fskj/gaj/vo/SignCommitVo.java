package com.fskj.gaj.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class SignCommitVo {
    /**
     * username  用户名
     password  密码
     mid  记录id

     */
    private String username;
    private String password;
    private String mid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
    public Map<String,String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", getUsername());
        map.put("password",getPassword());
        map.put("mid",getMid());
        return map;
    }
}
