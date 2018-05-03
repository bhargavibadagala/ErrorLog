<%@page import="com.google.gson.JsonArray"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="org.json.simple.*"%>


<link href="https://1.www.s81c.com/common/v18/css/www.css" rel="stylesheet">
<link href="//1.www.s81c.com/common/v17e/css/data.css" rel="stylesheet" title="www" type="text/css" />
<title>Failed Opportunities</title>
<style type="text/css">
/* body{
	 font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
} */
#ErrorLogs {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

#ErrorLogs td, #ErrorLogs th {
    border: 1px solid #ddd;
    padding: 8px;
}


/* #ErrorLogs tr:nth-child(even){background-color: #f2f2f2;}

#ErrorLogs tr:hover {background-color: #ddd;}

#ErrorLogs th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #4CAF50;
    color: white;
}
a:link, a:visited {
    color: black;
    padding: 14px 25px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    border: 1px solid;
    padding: 3px;
    box-shadow: 4px 1px;
}

.previous {
    background-color: #f1f1f1;
    color: black;
}

.next {
    background-color: #4CAF50;
    color: white;
}
a {
    text-decoration: none;
    display: inline-block;
    padding: 8px 16px;
}

a:hover {
    background-color: #ddd;
    color: black;
}
/* a:hover, a:active {
    background-color: red;
} */ */
</style>
</head>
<body id="ibm-com" class="ibm-type"><br>
<a href="back" style="float: left;margin-left: 12px;">Back</a>
	
<br><br>

<table  cellspacing="1" cellpadding="1" id="ErrorLogs">
	<tr style="background-color:gray;">
	<td>Error</td>
	<td>OpptyNum</td>
	
	<td>Status</td>
	<td>SOURCE</td>
	<td>TIMEPROCESSED</td>
	</tr>

<c:forEach var="errorBeanObj" items="${errDets}">
<tr>
<td>${errorBeanObj.errorstring}</td>
<td>${errorBeanObj.opptynum}</td>

<td>${errorBeanObj.status}</td>

<td>${errorBeanObj.source}</td>
<td>${errorBeanObj.timeprocessed}</td>
</tr>


</c:forEach>
</table>




</body>
</html>