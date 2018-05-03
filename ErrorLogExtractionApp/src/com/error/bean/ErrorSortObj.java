package com.error.bean;

import java.util.List;

public class ErrorSortObj {
	
	private List<ErrorLogBean> errList = null;

	private int errCount = 0;

	public List<ErrorLogBean> getErrList() {
		return errList;
	}

	public void setErrList(List<ErrorLogBean> errList) {
		this.errList = errList;
	}

	public int getErrCount() {
		return errCount;
	}

	public void setErrCount(int errCount) {
		this.errCount = errCount;
	}
	
	
	
	
	

}
