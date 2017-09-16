package com.fskj.gaj.vo;

import com.fskj.gaj.Util.PageQuery;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class MsgSearchCommitVo extends PageQuery{
    private String keys;

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }
    public Map<String,String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", page+"");
        map.put("pagesize",pagesize+"");
        map.put("keys",getKeys());
        return map;
    }
}
