package com.errorlog;

import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.error.bean.ErrorLogBean;
import com.error.bean.ErrorLogJsonParsing;
import com.error.bean.ErrorLogPojo;
import com.error.bean.ErrorSortObj;
import com.error.bean.Login;
import com.errorlog.service.ErrorLogService;
import com.google.gson.JsonElement;

@Controller
public class ErrorLogExtractionController {
	
	String nextJSP = "";
	
	@Autowired
	ErrorLogPojo logPojo;
	
	@Autowired
	ErrorLogService errorLogService;
	
	
	@RequestMapping(value= "/login", method=RequestMethod.POST)
	public ModelAndView authenticateUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") Login login) throws ConnectException{
		ModelAndView model = null;
		//ErrorLogService errorLogService = new ErrorLogService();
		String FailureMsg = "You are not authorized to access this application";
		String responseString= errorLogService.getEmpDetails(login);
		if(responseString.equalsIgnoreCase(FailureMsg)){
		model = new ModelAndView("index");
		model.addObject("errorMessage", FailureMsg);
		}else{
		String  response2String=  errorLogService.getEmpAuthorized(login);
		boolean blueGrpCheck=false;
		blueGrpCheck = response2String.contains("Gaia_Error_Log");
		if(blueGrpCheck){
			model = new ModelAndView("Home");
			JsonElement errorLogData = errorLogService.getTotalErrorsCount();
			ErrorLogJsonParsing errorLogJsonParsing = new ErrorLogJsonParsing();
			logPojo = errorLogJsonParsing.parseErrorLogData(errorLogData);
			List<ErrorSortObj> topErrs = errorLogService.searchForTopErrors(logPojo);
			//model.addObject("errorList",errorList.getErrorLogList());
			Calendar cal = Calendar.getInstance();
			int year  = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int day   = cal.get(Calendar.DAY_OF_MONTH);
			cal.clear();
			cal.set(year, month, day);
			java.sql.Date today = new java.sql.Date(cal.getTimeInMillis());
			System.out.println(today +"today");
			model.addObject("logPojo", logPojo);
			model.addObject("topErrs", topErrs);
			model.addObject("today", today);
			
			System.out.println("user exist in blue grp : " + blueGrpCheck);
		}else{
			model = new ModelAndView("index");
			model.addObject("errorMessage", FailureMsg);
			System.out.println("user doesn't exist in blue grp : " + blueGrpCheck);
			
		}
		
		
	}

		return model;
	}
	
	
	
	@RequestMapping(value= "/exportCSV")
	@Produces({"text/csv"})
	public ModelAndView exportCSV(HttpServletRequest request, HttpServletResponse response) throws ConnectException{
		ModelAndView model = new ModelAndView("Home");
		return model;
	}
	
	@RequestMapping(value= "/errorCount")
	public ModelAndView errorCount(HttpServletRequest request, HttpServletResponse response) throws ConnectException{
		System.out.println("errorCount");
		ModelAndView model = new ModelAndView("Failure");
		model.addObject("logPojo",logPojo);
		return model;
		
	}
	

	@RequestMapping(value= "/back")
	public ModelAndView back(HttpServletRequest request, HttpServletResponse response) throws ConnectException{
		System.out.println("errorCount");
		Calendar cal = Calendar.getInstance();
		int year  = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day   = cal.get(Calendar.DAY_OF_MONTH);
		cal.clear();
		cal.set(year, month, day);
		java.sql.Date today = new java.sql.Date(cal.getTimeInMillis());
		List<ErrorSortObj> topErrs = errorLogService.searchForTopErrors(logPojo);
		ModelAndView model = new ModelAndView("Home");
		model.addObject("logPojo",logPojo);
		model.addObject("topErrs", topErrs);
		model.addObject("today",today);
		return model;
		
	}
	
	@RequestMapping(value= "/search", method=RequestMethod.POST)
	public ModelAndView customSearch(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("errorLogBean") ErrorLogBean errorLogBean) throws ConnectException{
		ModelAndView mvc = new ModelAndView("Failure");
		//ErrorLogService errorLogService = new ErrorLogService();
		ErrorLogPojo pojo = errorLogService.customeSearch(errorLogBean, logPojo);
		mvc.addObject("logPojo",pojo);
		return mvc;
		
		
	}
	
	/*@RequestMapping(value= "/topErrors")
	public ModelAndView getTop10Errors(HttpServletRequest request, HttpServletResponse response) throws ConnectException{
		ModelAndView mvc = new ModelAndView("Failure");
		//ErrorLogService errorLogService = new ErrorLogService();
		List<ErrorSortObj> topErrs = errorLogService.searchForTopErrors(logPojo);
		mvc.addObject("topErrs",topErrs);
		return mvc;
		
		
	}*/
	
	@RequestMapping(value= "/getErrorDetails", method=RequestMethod.GET)
	public ModelAndView getErrDetail(HttpServletRequest request, HttpServletResponse response, @RequestParam(required=true) String key) throws ConnectException, UnsupportedEncodingException{
		ModelAndView mvc = new ModelAndView("ErrorDetail");
		String decodedKey = URLDecoder.decode(key, "UTF-8");
		List<ErrorLogBean> errDets = errorLogService.getErrorDetails(logPojo,decodedKey);
		mvc.addObject("errDets",errDets);
		return mvc;
		
		
	}
	
	
	
}
	


