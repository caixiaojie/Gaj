package com.fskj.gaj.request;

import android.content.Context;
import android.util.Log;

import com.fskj.gaj.BuildConfig;
import com.fskj.gaj.Remote.BaseListRequest;
import com.fskj.gaj.Remote.HttpUtils;
import com.fskj.gaj.Remote.ResultListInterface;
import com.fskj.gaj.Remote.ResultTVO;
import com.fskj.gaj.vo.RoomListResultVo;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class RoomListRequest extends BaseListRequest<String,RoomListResultVo> {
    public RoomListRequest(Context activeContext, String requestData, ResultListInterface<RoomListResultVo> listener) {
        super(activeContext, requestData, listener);
    }

    @Override
    protected ResultTVO<RoomListResultVo> Query_Process() throws IOException, Exception {
        String url = BuildConfig.SERVER_IP + "/mobile/roomlist.do";
        String jsonStr = HttpUtils.get(url);
        Log.e("RoomListRequest",jsonStr);
        ResultTVO<RoomListResultVo> vo = gson.fromJson(jsonStr,new TypeToken<ResultTVO<RoomListResultVo>>(){}.getType());
        return vo;
    }
}
