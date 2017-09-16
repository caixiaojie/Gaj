package com.fskj.gaj.request;

import android.content.Context;
import android.util.Log;

import com.fskj.gaj.BuildConfig;
import com.fskj.gaj.Remote.BaseListRequest;
import com.fskj.gaj.Remote.HttpUtils;
import com.fskj.gaj.Remote.ResultListInterface;
import com.fskj.gaj.Remote.ResultTVO;
import com.fskj.gaj.vo.MsgSearchCommitVo;
import com.fskj.gaj.vo.MsgSearchResultVo;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class MsgSearchRequest extends BaseListRequest<MsgSearchCommitVo,MsgSearchResultVo>{
    public MsgSearchRequest(Context activeContext, MsgSearchCommitVo requestData, ResultListInterface<MsgSearchResultVo> listener) {
        super(activeContext, requestData, listener);
    }

    @Override
    protected ResultTVO<MsgSearchResultVo> Query_Process() throws IOException, Exception {
        String url = BuildConfig.SERVER_IP + "/mobile/msgsearch.do";
        String jsonStr = HttpUtils.post(url,requestData.toMap());
        Log.e("MsgSearchRequest",jsonStr);
        ResultTVO<MsgSearchResultVo> vo = gson.fromJson(jsonStr,new TypeToken<ResultTVO<MsgSearchResultVo>>(){}.getType());
        return vo;
    }
}
