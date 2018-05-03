
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <meta name="viewport" content="initial-scale=1,user-scalable=no,maximum-scale=1">
                <meta http-equiv="Set-Cookie" content="PD-W3ID-REFERER=none; path=/; secure" />
                <link rel="stylesheet" type="text/css" href="https://w3id.sso.ibm.com:443/static/css/bundle.s41.css">
                    <title>IBM w3id</title>
	</head>
    <body id="body">
        <div class="wrapper">
            <!-- header -->
            <div id="header">
                <div class="inner">
                    <div id="logo"></div>
                </div> 
            </div>
            <!-- /header -->
            
            <div class="inner">
                <div class="container">
                    <div class="signin">
                        <div class="icon"></div>
                        <h1 id="w3idheader">Sign in with your <span class="name">w3<b>id</b></span></h1>
                        <div class="hidden error" id="errorDiv" > </div>
                     
                 
                      
                      
                        <form:form method="post" modelAttribute="login" action="login">
                        <c:if test="${!errorMessage}">
							<div style="color:red;"> 
								${errorMessage} 
							</div >
							</c:if>
							<br>
                            <input type="hidden" name="login-form-type" value="pwd"/>
                            <input id="desktop" type="email" class="desktop" name="email" aria-label="Your IBM email address (e.g. jdoe@us.ibm.com)" placeholder="Your IBM email address (e.g. jdoe@us.ibm.com)"/>
                            <input id="mobile" type="email" class="mobile"  name="emailAddress" placeholder="jdoe@us.ibm.com"/>
                            <input aria-label="password" type="password" name="password" placeholder="Password"/>
							<button id="btn_signin" class="btn_signin" type="submit">Sign In</button>
							<br>
							
                        </form:form>
                    </div>
                </div>
            </div>
            <div class="push"></div>
        </div>
        <!-- footer -->
        <div id="footer">
            <div class="inner">
                <div id="logo"></div>
            </div>
        </div>
        <!-- /footer -->
    </body>
</html>
