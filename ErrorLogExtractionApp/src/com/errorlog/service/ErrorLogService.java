package com.errorlog.service;

import java.net.ConnectException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.error.bean.ErrorLogBean;
import com.error.bean.ErrorLogPojo;
import com.error.bean.ErrorSortObj;
import com.error.bean.Login;
import com.error.bean.SearchParam;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ErrorLogService implements ApplicationContextAware{
	
	@Autowired
	private SearchParam searchParam;
	
	private ApplicationContext appContxt = null;
	

	public List<ErrorSortObj> searchForTopErrors(ErrorLogPojo logPojo){
		Map<String,List<ErrorLogBean>> uniqueErrMap = new HashMap<String,List<ErrorLogBean>>();
		List<ErrorLogBean> errorList = logPojo.getBean();
		Iterator<ErrorLogBean> errListItr = errorList.iterator();
		while(errListItr.hasNext()){
			ErrorLogBean errorObj = errListItr.next();
			String errorKey = errorObj.getErrorstring();
			if(uniqueErrMap.containsKey(errorKey)){
				List<ErrorLogBean> errList = uniqueErrMap.get(errorKey);
				errList.add(errorObj);
			}else {
				List<ErrorLogBean> errList = new ArrayList<ErrorLogBean>();
				errList.add(errorObj);
				uniqueErrMap.put(errorKey, errList);
			}
		}
		
		searchParam.setErrMap(uniqueErrMap);
		Set<ErrorSortObj> errSet = new TreeSet<ErrorSortObj>(new ErrorSetComp());
		Collection<List<ErrorLogBean>> valueColl = uniqueErrMap.values();
		Iterator<List<ErrorLogBean>> valeCollItr = valueColl.iterator();
		while(valeCollItr.hasNext()){
			List<ErrorLogBean> errList = valeCollItr.next();
			if(null != errList) {
				int size = errList.size();
				ErrorSortObj errObj = (ErrorSortObj) appContxt.getBean("errSortObj");
				errObj.setErrList(errList);
				errObj.setErrCount(size);
				errSet.add(errObj);
			}
		}
		
		int count = 0;
		List<ErrorSortObj> errSortList = new ArrayList<ErrorSortObj>();
		Iterator<ErrorSortObj> errItr = errSet.iterator();
		while(errItr.hasNext()){
			if(count < 10){
				errSortList.add(errItr.next());
			}else{
				break;
			}
			count++;
		}
		
	
		return errSortList;
		
		
	}

	public ErrorLogPojo customeSearch(ErrorLogBean bean, ErrorLogPojo logPojo){
		ErrorLogPojo errorLogPojo =new  ErrorLogPojo();
		ArrayList<ErrorLogBean> errorLogList = new ArrayList<ErrorLogBean>();
		//from cutome search
		String status = bean.getStatus();
		String opptyNum = bean.getOpptynum();
		String enddate = bean.getEnddate();
		String stdate = bean.getStdate();
		int size =  logPojo.getBean().size();
		// from pojo
		String opptyNum2 = "";
		String status2 = "";
		String timeProcessed = "";
		String source = "";
		String error = "";
		System.out.println(status+" "+opptyNum+" "+enddate+" "+stdate + " " +size);
			for(int i = 0;i<size;i++){
				opptyNum2 = logPojo.getBean().get(i).getOpptynum();
				status2 = logPojo.getBean().get(i).getStatus();
				timeProcessed = logPojo.getBean().get(i).getTimeprocessed();
				source = logPojo.getBean().get(i).getSource();
				error = logPojo.getBean().get(i).getErrorstring();
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 =null;
				Date date2 =null;
				Date date3 = null ;
					try {
						if(enddate !="" && stdate != ""){
							date1 = format.parse(stdate);
							date2 = format.parse(enddate);
							date3 = format.parse(timeProcessed);
							System.out.println(date1.compareTo(date3) + " " + date2.compareTo(date3));
						}
						if(status != "" && (enddate !="" && stdate != "")){
							System.out.println("status and end date is not null");
							if(opptyNum.equalsIgnoreCase(opptyNum2) && status.equalsIgnoreCase(status2)&&(date1.compareTo(date3) <= 0 && date2.compareTo(date3)>=0)){
								ErrorLogBean errorLogBean = new ErrorLogBean();
								errorLogBean.setOpptynum(opptyNum2);
								errorLogBean.setSource(source);
								errorLogBean.setErrorstring(error);
								errorLogBean.setStatus(status2);
								errorLogBean.setTimeprocessed(timeProcessed);
								errorLogList.add(errorLogBean);
							}
						}else if(status != ""){
							System.out.println("status is not null");
							if(opptyNum.equalsIgnoreCase(opptyNum2) && status.equalsIgnoreCase(status2)){
								ErrorLogBean errorLogBean = new ErrorLogBean();
								errorLogBean.setOpptynum(opptyNum2);
								errorLogBean.setSource(source);
								errorLogBean.setErrorstring(error);
								errorLogBean.setStatus(status2);
								errorLogBean.setTimeprocessed(timeProcessed);
								errorLogList.add(errorLogBean);
							}
						}else if(enddate !="" && stdate != ""){
							System.out.println(" end date is not null");
							if(opptyNum.equalsIgnoreCase(opptyNum2) &&(date1.compareTo(date3) <= 0 && date2.compareTo(date3)>=0)){
								ErrorLogBean errorLogBean = new ErrorLogBean();
								errorLogBean.setOpptynum(opptyNum2);
								errorLogBean.setSource(source);
								errorLogBean.setErrorstring(error);
								errorLogBean.setStatus(status2);
								errorLogBean.setTimeprocessed(timeProcessed);
								errorLogList.add(errorLogBean);
								
							}
							
							
					}else{
						System.out.println("only opptyNum is available");
						if(opptyNum.equalsIgnoreCase(opptyNum2)){
							ErrorLogBean errorLogBean = new ErrorLogBean();
							errorLogBean.setOpptynum(opptyNum2);
							errorLogBean.setSource(source);
							errorLogBean.setErrorstring(error);
							errorLogBean.setStatus(status2);
							errorLogBean.setTimeprocessed(timeProcessed);
							errorLogList.add(errorLogBean);
							
						}
						
					}
						
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
		System.out.println("errorLogList : " + errorLogList.size());
		errorLogPojo.setBean(errorLogList);
		
		
	}
			return errorLogPojo;
	}
	
	public String  getEmpDetails(Login login) throws ConnectException{
		System.out.println(login.getEmail() + " " + login.getPassword());
		String apiForEmpDetails = "https://workfromhomep1.w3ibm.mybluemix.net/IBM/authenticateTest";
		System.out.println("apiForEmpDetails : "+ apiForEmpDetails);
		Client restClient = Client.create();
	    WebResource webResource2 = restClient.resource(apiForEmpDetails);
	    String input = "{\"username\":\""+login.getEmail()+"\",\"password\":\""+login.getPassword()+"\"}";
		ClientResponse resp2 = webResource2.header("content-type", "application/json").post(ClientResponse.class,input);
		   if(resp2.getStatus() != 200){
	         System.err.println("Unable to connect to the server : " + resp2.getStatus() );
	       }
	     String output = resp2.getEntity(String.class);
	     System.out.println("response: "+output);
	    
	     return output;
	     
	   }
	public String  getEmpAuthorized(Login login) throws ConnectException{
		String apiForEmpDetails = "http://bluepages.ibm.com/BpHttpApisv3/slaphapi?ibmperson/(mail="+login.getEmail()+").list/bytext?ibm-allgroups";
		Client restClient = Client.create();
	    WebResource webResource2 = restClient.resource(apiForEmpDetails);
		ClientResponse resp2 = webResource2.header("content-type", "application/json").get(ClientResponse.class);
		   if(resp2.getStatus() != 200){
	         System.err.println("Unable to connect to the server : " + resp2.getStatus() );
	       }
	     String output = resp2.getEntity(String.class);
	    // System.out.println("response: "+output);
	    
	     return output;
	     
	   }
	public JsonElement  getTotalErrorsCount() throws ConnectException{
		Calendar cal = Calendar.getInstance();
		int year  = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day   = cal.get(Calendar.DAY_OF_MONTH);
		cal.clear();
		cal.set(year, month, day);
		java.sql.Date today = new java.sql.Date(cal.getTimeInMillis());
		System.out.println(today +"today");
		String apiForTotalErrorCount = "http://istart-rest-dev1.lexington.ibm.com/restws/ebi/GAIA/9114/?date="+today+"&format=j";
		System.out.println("apiForTotalErrorCount : "+ apiForTotalErrorCount);
		 String input = "{\"date\":\""+today+"\"}";
		Client restClient = Client.create();
	    WebResource webResource2 = restClient.resource(apiForTotalErrorCount);
		ClientResponse resp2 = webResource2.header("content-type", "application/json").post(ClientResponse.class,input);
		   if(resp2.getStatus() != 200){
	         System.err.println("Unable to connect to the server : " + resp2.getStatus() );
	       }
	     String output = resp2.getEntity(String.class);
	     JsonParser parser = new JsonParser();
		 JsonElement json = parser.parse(output);
	     return json;
	     
	     
	   }
	
	class ErrorSetComp implements Comparator<ErrorSortObj>{
		 
	    public int compare(ErrorSortObj e1, ErrorSortObj e2) {
	        return e2.getErrCount()-e1.getErrCount();
	    }
	}

	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		appContxt = arg0;
		
	} 
	
	public List<ErrorLogBean> getErrorDetails(ErrorLogPojo logPojo, String mapKey){
		Map<String,List<ErrorLogBean>> uniqueErrMap = searchParam.getErrMap();
		List<ErrorLogBean> errDetail = uniqueErrMap.get(mapKey);
		return errDetail;
		
		
	}
	
	

}
