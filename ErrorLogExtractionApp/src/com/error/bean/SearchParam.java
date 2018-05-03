package com.error.bean;

import java.util.List;
import java.util.Map;

public class SearchParam {
	
	Map<String,List<ErrorLogBean>> errMap = null;

	public Map<String, List<ErrorLogBean>> getErrMap() {
		return errMap;
	}

	public void setErrMap(Map<String, List<ErrorLogBean>> errMap) {
		this.errMap = errMap;
	}
	
	

}
