package com.fskj.gaj.Remote;

import android.app.Activity;
import android.content.Context;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;

public abstract class BaseObjRequest<T,K> {


	//实际请求
	protected abstract ResultVO<K> Query_Process() throws IOException,Exception;

	private ResultObjInterface<K> listener;
	protected T requestData;
	protected Gson gson;
	protected Context activeContext;
	private Thread th;

	private boolean cancel;

	private RequestHandler requestHandler=new RequestHandler(){
		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {
			boolean cando=false;
			if(activeContext instanceof Activity){
				Activity m=(Activity)activeContext;
				if(m.isDestroyed()==false) {
					cando = true;
				}
			}

			if (cando==true&&cancel == false && listener != null) {
				switch (msg.what) {
					case 0:
						ResultVO<K> obj = (ResultVO<K>) msg.obj;
						if (obj.success()) {
							listener.success(obj);
						} else {
							listener.error(obj.getMsg());
						}
						break;
					case 2:
						listener.error(msg.obj.toString());
						break;
				}
			}
		}
	};
 
	
	public BaseObjRequest(Context activeContext, T requestData, ResultObjInterface<K> listener) {
		this.activeContext = activeContext;
		this.requestData = requestData;
		this.listener = listener;
		this.gson=new Gson();
	}

	public void send() {
		cancel=false;
		if (RequestHandler.checkConnection(activeContext) == false) {
			Message msg = requestHandler.obtainMessage();
			msg.what = 2;
			msg.obj ="当前网络不可用";
			 msg.sendToTarget();
			return;
		}

		th = new Thread() {
			@Override
			public void run() {
				Message msg = requestHandler.obtainMessage();
				try {
					ResultVO<K> result = Query_Process();
					if (result != null) {
						msg.what = 0;
						msg.obj = result;
					} else {
						throw new Exception("查询失败");
					}
				}catch (IOException e){
					msg.what = 2;
					msg.obj = "服务器失联了";
					e.printStackTrace();
				} catch (Exception e) {
					msg.what = 2;
					msg.obj = "系统异常";
					e.printStackTrace();
				}
				msg.sendToTarget();
			}
		};
		th.start();

	}
	
	
}
