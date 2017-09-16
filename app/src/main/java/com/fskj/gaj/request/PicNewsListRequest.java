package com.fskj.gaj.request;

import android.content.Context;
import android.util.Log;

import com.fskj.gaj.BuildConfig;
import com.fskj.gaj.Remote.BaseListRequest;
import com.fskj.gaj.Remote.HttpUtils;
import com.fskj.gaj.Remote.ResultListInterface;
import com.fskj.gaj.Remote.ResultTVO;
import com.fskj.gaj.Util.PageQuery;
import com.fskj.gaj.vo.PicNewsListResultVo;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class PicNewsListRequest extends BaseListRequest<PageQuery,PicNewsListResultVo> {
    public PicNewsListRequest(Context activeContext, PageQuery requestData, ResultListInterface<PicNewsListResultVo> listener) {
        super(activeContext, requestData, listener);
    }

    @Override
    protected ResultTVO<PicNewsListResultVo> Query_Process() throws IOException, Exception {
        String url = BuildConfig.SERVER_IP + "/mobile/picnewslist.do?"+requestData.toString();
        String jsonStr = HttpUtils.get(url);
        Log.e("PicNewsListResultVo",jsonStr);
        ResultTVO<PicNewsListResultVo> vo = gson.fromJson(jsonStr,new TypeToken<ResultTVO<PicNewsListResultVo>>(){}.getType());
        return vo;
    }
}
