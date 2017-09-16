package com.fskj.gaj.vo;

import com.fskj.gaj.Util.PageQuery;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class NoticeListCommitVo extends PageQuery{
    private String type;//type=gonggao | huiyi | tongbao

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("page=%d&pagesize=%d&type=%s",page,pagesize,getType());
    }
}
