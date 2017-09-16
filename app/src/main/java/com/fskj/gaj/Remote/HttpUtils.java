package com.fskj.gaj.Remote;

import android.util.Log;


import com.fskj.gaj.BuildConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {

    public static String post(String uri, Map<String,String> body ) throws IOException,Exception {

        try {
            String result = null;

            OkHttpClient _client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();

            Request.Builder builder=new Request.Builder().url(uri);
            //请求头
            Map<String,String> head = new HashMap<>();
            if(head!=null&&head.size()>0){
                for (Map.Entry<String, String> entry : head.entrySet()) {
                    builder.addHeader(entry.getKey(),entry.getValue());
                }
            }
            FormBody.Builder formBody = new FormBody.Builder();
            //请求体
            if(body!=null&&body.size()>0){
                for (Map.Entry<String, String> entry : body.entrySet()) {
                    formBody.add(entry.getKey(),entry.getValue());
                }
                if (BuildConfig.DEBUG) {
                    Log.e("gaj",formBody.toString());
                }
            }
            RequestBody requestBody = formBody.build();

            Request request = builder.post(requestBody).build();

            Response response = _client.newCall(request).execute();

            int rcode=response.code();
            Log.i("rcode","url="+uri+"  rcode="+rcode);
            if (response.isSuccessful()){
                result = response.body().string();

            } else {
                Log.i("result","response.code="+response.code()+"");
                throw new IOException();
            }
            if (BuildConfig.DEBUG) {
                Log.i("result", "" + result);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("服务器连接失败或超时", e);
        }
    }

    public static String get(String uri) throws IOException,Exception {

        try {
            String result = null;
            OkHttpClient _client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();


            Request.Builder builder=new Request.Builder().url(uri);


            Request request = builder.get().build();



            Response response = _client.newCall(request).execute();

            int rcode=response.code();
            if (BuildConfig.DEBUG) {
                Log.e("gaj",uri);
            }
            Log.i("rcode","url="+uri+"  rcode="+rcode);
            if (response.isSuccessful()){
                result = response.body().string();
//                result = response.body().toString();

            } else {
                Log.i("result","response.code="+response.code()+"");
                throw new IOException();
            }
            if (BuildConfig.DEBUG) {
                Log.i("result", "" + result);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("服务器连接失败或超时", e);
        }
    }
}