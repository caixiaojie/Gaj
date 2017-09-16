package com.fskj.gaj.request;

import android.content.Context;
import android.util.Log;

import com.fskj.gaj.BuildConfig;
import com.fskj.gaj.Remote.BaseListRequest;
import com.fskj.gaj.Remote.HttpUtils;
import com.fskj.gaj.Remote.ResultListInterface;
import com.fskj.gaj.Remote.ResultTVO;
import com.fskj.gaj.vo.TypeListResultVo;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class TypeListRequest extends BaseListRequest<String,TypeListResultVo> {
    public TypeListRequest(Context activeContext, String requestData, ResultListInterface<TypeListResultVo> listener) {
        super(activeContext, requestData, listener);
    }

    @Override
    protected ResultTVO<TypeListResultVo> Query_Process() throws IOException, Exception {
        String url = BuildConfig.SERVER_IP + "/mobile/typelist.do?";
        String jsonStr = HttpUtils.get(url);
        Log.e("TypeListRequest",jsonStr);
        ResultTVO<TypeListResultVo> vo = gson.fromJson(jsonStr,new TypeToken<ResultTVO<TypeListResultVo>>(){}.getType());
        return vo;
    }
}
