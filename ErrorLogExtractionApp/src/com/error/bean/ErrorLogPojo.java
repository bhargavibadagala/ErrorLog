package com.error.bean;
import java.util.ArrayList;
import com.google.gson.JsonArray;


public class ErrorLogPojo {
	
	int errorCount;
	JsonArray errorList;
	ArrayList<ErrorLogBean> bean;
	ArrayList<String> mostFrequentErrors;
	
	
	public ArrayList<String> getMostFrequentErrors() {
		return mostFrequentErrors;
	}
	public void setMostFrequentErrors(ArrayList<String> mostFrequentErrors) {
		this.mostFrequentErrors = mostFrequentErrors;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	public JsonArray getErrorList() {
		return errorList;
	}
	public void setErrorList(JsonArray errorList) {
		this.errorList = errorList;
	}
	public ErrorLogPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<ErrorLogBean> getBean() {
		return bean;
	}
	public void setBean(ArrayList<ErrorLogBean> bean) {
		this.bean = bean;
	}
	
	
	
	
	

}
