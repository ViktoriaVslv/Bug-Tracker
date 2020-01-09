<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="model.BDBugs"%>
<%@ page import="model.BDUsers"%>
<%@ page import="javax.servlet.http.*"%>

<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<link rel="stylesheet" href="demo.css" />
		<title>Tracker Bug</title>
	</head>
	<body>
		<div class="login-page">
        	<div class="form">
            	<form class="login-form" action = "LoginServlet" method="post">
            		<div class="d3"><h3><span>LOGIN</span></h3></div>
                	<input type="text" name = "login" placeholder="Login" value/>
                    <input type="password" name = "password" placeholder="Password" value/>
		   			<input type= "submit" value="Login"/>
		    		<p hidden value ="wrCred">Login or password are wrong</p> 
		    		<p class="message" id="mess">Don't have a profile? <a href="signup.jsp">Sign up</a></p>
                    <p class="message"><a href="table.jsp">Try without authorization</a></p>
                    <% RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/LoginServlet");
        			String user = (String) session.getAttribute("alert");
        			if (user != null){
        			if(user.equals("noname")){%>
        				<script> alert('NO NAME or PASSWORD')</script>
        			<%} 
        			if(user.equals("nosuchuser")){%>
        				<script> alert('NO SUCH USER')</script>
        			<%}
        			}
        			session.removeAttribute("alert");
        			%>
		  		</form>
            </div>
	</body>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
	<script src="boot.js" type="text/javacsript"></script>
</html>