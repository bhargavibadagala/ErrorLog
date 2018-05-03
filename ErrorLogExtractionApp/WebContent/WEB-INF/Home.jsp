<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<title>Home Page</title>
<script src="https://1.www.s81c.com/common/stats/ida_stats.js"></script>
<link href="https://1.www.s81c.com/common/v18/css/www.css" rel="stylesheet">
<script src="https://1.www.s81c.com/common/v18/js/www.js"></script>
<script type="text/javascript">
var datefield=document.createElement("input")
datefield.setAttribute("type", "date")
if (datefield.type!="date"){ //if browser doesn't support input type="date", load files for jQuery UI Date Picker
document.write('<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />\n')
document.write('<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"><\/script>\n')
document.write('<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"><\/script>\n') 
}
</script>

<script>
if (datefield.type!="date"){ //if browser doesn't support input type="date", initialize date picker widget:
jQuery(function($){ //on document.ready
$('#stdate').datepicker();
$('#enddate').datepicker();
})
}
</script> 
<style type="text/css">
body{
font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;

}
input{
    border: solid;
}
select{
	    border: solid;
}
</style>
</head>
<body>
<div id="ibm-leadspace-head" style="background: #4178BE">
	<div style="padding: 10px 0px 0px 40px;""> 
		
		<br>
		
<h1 class="ibm-bold ibm-h1 ibm-light ibm-textcolor-white-core" id="ibm-pagetitle-h1" ibm-padding-bottom-0"=""><strong>Error Log Application</strong></h1> 
<br><br>
			<p><span class="ibm-h4 ibm-textcolor-white-core">Search Errors using Oppty #, Status and Date range!!</span>
		</p>
	</div>
</div>
<br>
<div id="error" style="padding:10px 0px 0px 40px;">
<h3 class="ibm-h3 ibm-bold">Error Log</h3>
<a href="errorCount">Total Errors : ${logPojo.errorCount}</a><br><br>
<h3 class="ibm-h3 ibm-bold">Top 10 Errors</h3>
<c:forEach var="errorSortObj" items="${topErrs}">
	<c:forEach var="errorBeanObj" items="${errorSortObj.errList}" end="0">
		<c:set var="shortDesc" value="${fn:substring(errorBeanObj.errorstring, 0, 40)}" />
		<c:set var="errorString" value="${errorBeanObj.errorstring}" scope="request"/>
		<%
		 String key = URLEncoder.encode(String.valueOf(request.getAttribute("errorString")), "UTF-8");
		 request.setAttribute("mapKey", key);
		%>
		<a href="getErrorDetails?key=${requestScope.mapKey}">${shortDesc} : ${errorSortObj.errCount}</a><br>
	</c:forEach>
</c:forEach>
<br><br>
<a href="http://istart-rest-dev1.lexington.ibm.com/restws/ebi/GAIA/9114/?date=${today}&format=c" download target="_blank"> Export CSV</a> 
<br><br>

<h3 class="ibm-h3 ibm-bold">Custom Search</h3>
<form:form action="search" modelAttribute="errorLogBean" method="post" >
  Oppty # &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="opptynum" id="opptynum" required="required"><br><br>
  Status &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="status" id="status">
  <option value="">select</option> 
    <option value="true">true</option>
    <option value="false">false</option>
    <option value="initial">Initial</option>
  </select><br><br>
  Start date &nbsp;<input type="date" name="stdate" id="stdate">
  End date &nbsp;<input type="date" name="enddate" id="enddate"><br><br>
  <input class="ibm-btn-pri ibm-btn-small ibm-btn-blue-50" name="ibm-sign-in" value="Search" type="submit">
 </form:form>
</div>
</body>
</html>