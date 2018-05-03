package com.error.bean;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ErrorLogJsonParsing {

	
	public ErrorLogPojo parseErrorLogData(JsonElement errorLogData){
		ErrorLogPojo pojo = new ErrorLogPojo();
		//ErrorLogListBean errorList = new ErrorLogListBean();
		ArrayList<ErrorLogBean> errorLogList = new ArrayList<ErrorLogBean>();
		 int counter;
		ArrayList<String> frequentErrors = new ArrayList<String>();
		JsonObject jsonErrorLogObj;
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonErrorLogObject = (JsonObject)jsonParser.parse(errorLogData.toString());
		jsonErrorLogObj = jsonErrorLogObject.getAsJsonObject().getAsJsonObject("response");
		int totoalRowCount = jsonErrorLogObj.getAsJsonObject().getAsJsonArray("metadata").get(0).getAsJsonObject().get("totalrowcount").getAsInt();
		pojo.setErrorCount(totoalRowCount);
		System.out.println("totoalRowCount : " + totoalRowCount);
		JsonArray resultsetArray = jsonErrorLogObj.getAsJsonObject().getAsJsonArray("resultset");
		pojo.setErrorList(resultsetArray);
		for(int i=0;i<resultsetArray.size();i++){
			counter=i;
			ErrorLogBean errorLog = new ErrorLogBean();
			JsonObject row = resultsetArray.get(i).getAsJsonObject();
			String error = row.getAsJsonObject("row").getAsJsonObject().getAsJsonPrimitive("ERROR").getAsString();
			String opptyNum = row.getAsJsonObject("row").getAsJsonObject().getAsJsonPrimitive("OPPTYNUM").getAsString();
			String source = row.getAsJsonObject("row").getAsJsonObject().getAsJsonPrimitive("SOURCE").getAsString();
			String status = row.getAsJsonObject("row").getAsJsonObject().getAsJsonPrimitive("STATUS").getAsString();
			String timeProcessed = row.getAsJsonObject("row").getAsJsonObject().getAsJsonPrimitive("TIMEPROCESSED").getAsString();
			errorLog.setErrorstring(((error).equalsIgnoreCase("null") ? " " :error));
			errorLog.setOpptynum(((opptyNum).equalsIgnoreCase("null") ? " " :opptyNum));
			errorLog.setSource(((source).equalsIgnoreCase("null") ? " " :source));
			errorLog.setStatus(((status).equalsIgnoreCase("null") ? " " :status));
			errorLog.setTimeprocessed(((timeProcessed).equalsIgnoreCase("null") ? " " :timeProcessed));
			errorLogList.add(errorLog);
			
			frequentErrors.add(counter, error);
	}
		
			pojo.setBean(errorLogList);
			pojo.setMostFrequentErrors(frequentErrors);
		return pojo;
		
		
	}
//public WorkStatus parseEmployeeData(JsonElement workData){
//		WorkStatus workstatus = new WorkStatus();
//		
//		JsonParser jsonParser = new JsonParser();
//		JsonObject jsonWorkObject = (JsonObject)jsonParser.parse(workData.toString());
//		System.out.println("jsonWorkObject :" + jsonWorkObject);
//		workstatus.setFullName(((jsonWorkObject.getAsJsonObject().get("fullName").getAsString()).equalsIgnoreCase("null") ? " " :jsonWorkObject.getAsJsonObject().get("fullName").getAsString()));
//			workstatus.setEmployeeId(((jsonWorkObject.getAsJsonObject().get("employeeId").getAsString()).equalsIgnoreCase("null") ? " " :jsonWorkObject.getAsJsonObject().get("employeeId").getAsString()));
//			workstatus.setImageUrl(((jsonWorkObject.getAsJsonObject().get("imageUrl").getAsString()).equalsIgnoreCase("null") ? " " :jsonWorkObject.getAsJsonObject().get("imageUrl").getAsString()));
//	
//		return workstatus;
//		
//	}
}
