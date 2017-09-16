package com.fskj.gaj.request;

import android.content.Context;
import android.util.Log;

import com.fskj.gaj.BuildConfig;
import com.fskj.gaj.Remote.BaseListRequest;
import com.fskj.gaj.Remote.HttpUtils;
import com.fskj.gaj.Remote.ResultListInterface;
import com.fskj.gaj.Remote.ResultTVO;
import com.fskj.gaj.vo.SignListCommitVo;
import com.fskj.gaj.vo.SignListResultVo;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class SignListRequest extends BaseListRequest<SignListCommitVo,SignListResultVo> {
    public SignListRequest(Context activeContext, SignListCommitVo requestData, ResultListInterface<SignListResultVo> listener) {
        super(activeContext, requestData, listener);
    }

    @Override
    protected ResultTVO<SignListResultVo> Query_Process() throws IOException, Exception {
        String url = BuildConfig.SERVER_IP + "/mobile/signlist.do?"+requestData.toString();
        String jsonStr = HttpUtils.get(url);
        Log.e("SignListRequest",jsonStr);
        ResultTVO<SignListResultVo> vo = gson.fromJson(jsonStr,new TypeToken<ResultTVO<SignListResultVo>>(){}.getType());
        return vo;
    }
}
