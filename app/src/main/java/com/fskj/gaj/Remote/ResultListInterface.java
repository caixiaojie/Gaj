package com.fskj.gaj.Remote;



public interface ResultListInterface<T> {
	 void success(ResultTVO<T> data);
	void error(String errmsg);

}
