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
            	<form class="signin-form" action = "SigninServlet" method="post">
            		<div class="d3"><h3><span>SIGNIN</span></h3></div>
                	<input type="text" name = "login" placeholder="Login" value/>
                    <input type="password" name = "password" placeholder="Password" value/>
                    <input type="password" name = "password_two" placeholder="Confirm password" value/>
                    <input type= "submit" value="Signin"/>
                    <% RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/SignupServlet");
        			String user = (String) session.getAttribute("alert");
        			if (user != null){
        			if(user.equals("noname")){%>
        				<script> alert('NO NAME or PASSWORD')</script>
        			<%} 
        			if(user.equals("loginallowed")){%>
        				<script> alert('Login isnt allowed')</script>
        			<%}
        			if(user.equals("nopass")){%>
    				<script> alert('Password doesnt match')</script>
    				<%}
        			}
        			session.removeAttribute("alert");
        			%>
                </form>
            </div>
      	</div>
	</body>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
	<script src="boot.js" type="text/javacsript"></script>
</html>