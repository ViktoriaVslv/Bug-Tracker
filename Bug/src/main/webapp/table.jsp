<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import = "java.io.PrintWriter"%>
<%@ page import = "javax.servlet.*"%>
<%@ page import = "java.util.*"%>
<%@ page import = "java.sql.*"%>
<%@ page import="model.BDBugs"%>
<%@ page import="model.BDUsers"%>
<%@ page import = "javax.servlet.http.*"%>
<html lang="en">
	<head>
    	<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<meta http-equiv="X-UA-Compatible" content="ie=edge">
    	<link rel= "stylesheet" href="table.css" />
    	<title>Tracker Bug</title>
	</head>
	<body> 
        <div class="table-page">
        	<div class="form"><%RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/LoginServlet");
        		String user = (String) session.getAttribute("login");
        		boolean hidden = false; 
        		if (user != null)
          			hidden = false; 
        		else 
          			hidden = true;
        
        		if(hidden == false){%>
					<form class="message" action = "LogoutServlet" method="post">
						<input type= "submit" name ="bt" value ="Logout"/>
					</form>
				<%} %>
                <form class="table-form" action = "Table" method="post">
                  	<div class="d3"><h3><span>Bug Tracker</span></h3></div>
      					<%if (hidden == false){%>
      						<div class="d4"><h3>Hello, <%=user%></h3></div>
        					<input type= "submit" id = "bug" name ="bt" value ="Add bug/feature"/>
        
      					<%} 
      					else {%>    
      						<input type= "submit" id = "bug" value ="Add bug/feature" hidden = "<%=hidden%>"/>
      					<%}%> 
      					<input type="text" name = "poisk" placeholder="Name of bug" value/> 
        				<input type= "submit" id = "search"  name ="bt" value ="Search"/>
     
			<%BDBugs bugs = new BDBugs();
            try {%>
            	<table>
					<tr><th>Name</th><th>Description</th><th>Form</th><th>Status</th><th>Date</th><% 
					RequestDispatcher dispatcher1 = this.getServletContext().getRequestDispatcher("/Table");
					String nameBug= (String) session.getAttribute("name");
					session.removeAttribute("name");
					if(nameBug==null){
						ArrayList b = bugs.getSortedBug();
						for(int i=0; i<b.size(); i++){
							ArrayList all =bugs.getAll((String)b.get(i));
							String info = (String)all.get(5);
		                 	if(info.length()>=30)
		                 		info=info.substring(0,30)+"...";%>
		                 	<tr><td><p class="message"><input type= "submit" name ="bt"  value ="<%=((String)all.get(1))%>"></p> </td><td><%=info%></td><td><%=((String)all.get(6))%></td><td><%=((String)all.get(0))%></td><td><%=((String)all.get(4))%></td>
						<%}
					}
					else{
						ArrayList b = bugs.getSortedBug();
						for(int i=0; i<b.size(); i++){
							if(((String)b.get(i)).startsWith(nameBug)){
								ArrayList all =bugs.getAll((String)b.get(i));
								String info = (String)all.get(5);
		                 		if(info.length()>=30)
		                 			info=info.substring(0,30)+"...";%>
		                 		<tr><td><p class="message"><input type= "submit" name ="bt"  value ="<%=((String)all.get(1))%>"></p> </td><td><%=info%></td><td><%=((String)all.get(6))%></td><td><%=((String)all.get(0))%></td><td><%=((String)all.get(4))%></td>
							<%}
	                   }
	               }%>
				</table> 
			<%}
        	catch (Exception e) {
           		 e.printStackTrace();
        	}%>
			</form>
        	</div>
    	</div>
	</body>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
	<script src="boot.js" type="text/javacsript"></script>
</html>
