package com.fskj.gaj.request;

import android.content.Context;
import android.util.Log;

import com.fskj.gaj.BuildConfig;
import com.fskj.gaj.Remote.BaseListRequest;
import com.fskj.gaj.Remote.HttpUtils;
import com.fskj.gaj.Remote.ResultListInterface;
import com.fskj.gaj.Remote.ResultTVO;
import com.fskj.gaj.vo.DutyResultVo;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class DutyRequest extends BaseListRequest<String,DutyResultVo> {
    public DutyRequest(Context activeContext, String requestData, ResultListInterface<DutyResultVo> listener) {
        super(activeContext, requestData, listener);
    }

    @Override
    protected ResultTVO<DutyResultVo> Query_Process() throws IOException, Exception {
        String url = BuildConfig.SERVER_IP + "/mobile/duty.do";
        String jsonStr = HttpUtils.get(url);
        Log.e("DutyRequest",jsonStr);
        ResultTVO<DutyResultVo> vo = gson.fromJson(jsonStr,new TypeToken<ResultTVO<DutyResultVo>>(){}.getType());
        return vo;
    }
}
