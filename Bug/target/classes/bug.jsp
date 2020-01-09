<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="model.BDUsers"%>
<%@ page import="model.BDBugs"%>
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
				<form class="bag-form" action="MakeBug" method="post">
					<%RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Table");
					String change = (String) session.getAttribute("change");
					BDBugs bugs = new BDBugs();
					BDUsers users = new BDUsers();
					ArrayList usersList = new ArrayList();
					ArrayList parents = new ArrayList();
					if (session.getAttribute("change") == null) {
						try {
							usersList = users.getAllUsers();
							parents = bugs.getAllBugs();
							String u=" ";%>
							Name*:<input type="text" name="name" placeholder="Name" value /> 
							Description:<input type="text" name="description" placeholder="Description" value />
							<p>
								Parent: <select name="parent">
									<option><%=u%></option>
									<%for (int i = 0; i < parents.size(); i++) {%>
										<option><%=parents.get(i)%></option>
									<%}%>
								</select>
							</p>
							<p>
								To*: <select name="towho">
									<%for (int i = 0; i < usersList.size(); i++) {%>
									<option><%=usersList.get(i)%></option>
									<%}%>
								</select>
							</p>
						<%
						users.close();
						} 
					catch (Exception e) {
						e.printStackTrace();
					}%>
					<p>
						Form*: <select name="list">
							<option>Bug</option>
							<option>Feature</option>
						</select>
					</p>
					<p>
						Status*: <select name="list1">
							<option>Open</option>
							<option>Close</option>
						</select>
					</p>
					<p class="message">*Required fields</p>
					<input type="submit" name="m" value="Save new bug" />

					<%}
					else {
						try {
						
							ArrayList all =bugs.getAll(change);
							usersList = users.getAllUsers();
							parents = bugs.getAllBugs();%>
							<h3>Change Bug</h3>
							Name: <input type="text" name="name" value="<%=(String)all.get(1)%>">
							Description: <input type="text" name="description" value="<%=(String)all.get(5)%>">
							<p>
								Form: <select name="list">
									<%if (((String)all.get(6)).equals("Bug")) {%>
										<option>Bug</option>
										<option>Feature</option>
									<%} 
									else {%>
										<option>Feature</option>
										<option>Bug</option>
									<%}%>
								</select>
							</p>
							<p>
								Status: <select name="list1">
									<%if (((String)all.get(0)).equals("Open")) {%>
										<option>Open</option>
										<option>Close</option>
									<%}
									else {%>
										<option>Close</option>
										<option>Open</option>
									<%}%>
								</select>
							</p>
							<p>
								To: <select name="towho">
									<option><%=(String)all.get(3)%></option>
									<%usersList.remove(all.get(3));
									for (int i = 0; i < usersList.size(); i++) {%>
										<option><%=(String)usersList.get(i)%></option>
									<%}%>
								</select>
							</p>
							<p>
								Parent: <select name="parent">
									<option><%=(String)all.get(7)%></option>
									<%parents.remove(all.get(1));
									parents.remove(all.get(7));
									//ListBug l = new ListBug(parents, (String)all.get(1), true);
									parents=bugs.newL(parents, (String)all.get(1));
									for (int i = 0; i < parents.size(); i++) {
										if (!parents.get(i).equals(all.get(7)))%>
											<option><%=(String)parents.get(i)%></option>
									<%}%>
								</select>
							</p>
							<input type="submit" name="m" value="Save" />
							<%bugs.close();	
						}
						catch (Exception e) {
							e.getStackTrace();
						}
						bugs.close();
					}
					dispatcher = this.getServletContext().getRequestDispatcher("/MakeBug");
        			String user = (String) session.getAttribute("alert");
        			if (user != null){
        				if(user.equals("noname")){%>
        					<script> alert('NO NAME')</script>
        				<%} 
        				if(user.equals("ex")){%>
        					<script> alert('Bug/Feature exists')</script>
        				<%}
        				if(user.equals("close")){%>
    						<script> alert("You can't close the bug. Nested bugs are not closed.")</script>
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
