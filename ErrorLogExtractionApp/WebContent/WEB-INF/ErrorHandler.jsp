	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Work Status</title>


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript" src="js/workstatus.js"></script>


<link rel="stylesheet" href="css/modal.css" type="text/css" />
<link rel="stylesheet" href="css/layout.css" type="text/css" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/button.css" type="text/css" />
<link rel="stylesheet" href="css/logout.css" type="text/css" />

<%-- <jsp:useBean id="empdetails" scope="session" class="com.workstatus.bean.WorkStatus"/> --%>


 
<style>
* {
    box-sizing: border-box;
}
.row::after {
    content: "";
    clear: both;
    display: table;
}
[class*="col-"] {
    float: left;
    padding: 15px;
}

html {
    font-family: "Lucida Sans", sans-serif;
}
#left{
width:50%;
float:left;
}
#middle{
float:left;
margin:0 auto;
}
#right{
width:3%;
float:right;
}
.header {
    background-color: #1D3458;
    color: #ffffff;
	font-family:HelveticaNeue;
	font-weight:bold;
	font-size:18px;
    padding: 15px;
	height:90px;
}
.menu ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
}
.menu li {
    padding: 8px;
    margin-bottom: 7px;
    background-color: #33b5e5;
    color: #ffffff;
    box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
}
.menu li:hover {
    background-color: #0099cc;
}
.aside {
    background-color: #33b5e5;
    padding: 15px;
    color: #ffffff;
    text-align: center;
    font-size: 14px;
    box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
}
.footer {
    background-color: #0099cc;
    color: #ffffff;
    text-align: center;
    font-size: 12px;
    padding: 15px;
}
/* For mobile phones: */
[class*="col-"] {
    width: 100%;
}
.logoutImg{
width:40px;
height:40px;
}
.hereIam{
margin: 0px 0px 0px 15px;
font-size: 20px;
}
@media only screen and (min-width: 600px) {
    /* For tablets: */
    .col-m-1 {width: 8.33%;}
    .col-m-2 {width: 16.66%;}
    .col-m-3 {width: 25%;}
    .col-m-4 {width: 33.33%;}
    .col-m-5 {width: 41.66%;}
    .col-m-6 {width: 50%;}
    .col-m-7 {width: 58.33%;}
    .col-m-8 {width: 66.66%;}
    .col-m-9 {width: 75%;}
    .col-m-10 {width: 83.33%;}
    .col-m-11 {width: 91.66%;}
    .col-m-12 {width: 100%;}
}
@media only screen and (min-width: 768px) {
    /* For desktop: */
    .col-1 {width: 8.33%;}
    .col-2 {width: 16.66%;}
    .col-3 {width: 25%;}
    .col-4 {width: 33.33%;}
    .col-5 {width: 41.66%;}
    .col-6 {width: 50%;}
    .col-7 {width: 58.33%;}
    .col-8 {width: 66.66%;}
    .col-9 {width: 75%;}
    .col-10 {width: 83.33%;}
    .col-11 {width: 91.66%;}
    .col-12 {width: 100%;}
}
</style>
</head>
<body style="margin:0">

<div class="header">
  <div>
			
			<div id="left">
<img id="" src="image/Icon.png">
<span class="hereIam">Here I AM</span>
   </div>

<div id="right">

 <span class="tooltip">
 <img class="logoutImg" onmouseover="return OverFunction()"  src="${empdetails.imageUrl}" style="margin-top: 10px;"/>
 <span class="tooltiptext" id="sp">
 ${empdetails.fullName}<br>
 ${empdetails.employeeId}<br>
  <a  href="loginStatus.jsp" >Logout</a>

</span>
</span>
</div>


<!-- <div id="right">
 <a class="tooltip" href="loginStatus.jsp" onmouseover="return OverFunction()" onmouseout="return OutFunction()">
  <span id="sp" class="tooltiptext">Logout</span><img id="logoutImg" src="image/logout.jpg" style="margin-top: 10px;"></a>
   </div> -->



<span id="middle">
    Week

</span><br>
			<div id="show">
			</div>



		</div>
</div>


<h2 style="color:red">Opps, something went wrong : ${errorMsg}</h2>

</body>
</html>
