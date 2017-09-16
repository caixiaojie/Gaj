package com.fskj.gaj.Remote;

import java.util.ArrayList;

/**
 * @Description 通用返回类（数组�?
 * @Company 重庆伏守科技有限公司
 * @Author zhengwei
 * @Date 2015-1-23
 */
public class ResultTVO<T> {

	private int code;
	private String msg;
	private ArrayList<T> data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg == null ? "系统出错" : msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ArrayList<T> getData() {
		return data;
	}


	public void setData(ArrayList<T> data) {
		this.data = data;
	}


	/**
	 * 操作成功
	 *
	 * @return
	 */
	public boolean success() {
		return code==1;
	}




}
