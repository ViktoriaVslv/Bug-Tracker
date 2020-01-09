<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import = "java.io.PrintWriter"%>
<%@ page import = "javax.servlet.*"%>
<%@ page import = "java.util.*"%>
<%@ page import = "java.sql.*"%>
<%@ page import = "model.*"%>
<%@ page import = "javax.servlet.http.*"%>

<html lang="en">
	<head>
   		<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<meta http-equiv="X-UA-Compatible" content="ie=edge">
    	<link rel= "stylesheet" href="demo.css" />
    	<title>Tracker Bug</title>
	</head>
	<body> 
        <div class="login-page">
        	<div class="form">
            	<form class="table-form" action = "Info" method="post">  
            	<script type="text/javascript">
  					function test() {
  						if (confirm("The bug and nested bugs will be dele?ted. Delete?")){ 
  							window.close()}
  						}
				</script>
		    		<%RequestDispatcher dispatcher1 = this.getServletContext().getRequestDispatcher("/Table");
					String nameBug= (String) session.getAttribute("nameBug");
					session.removeAttribute("nameBug");
					
					BDBugs bugs = new BDBugs();
            		try {
               			ArrayList bug =bugs.getAll(nameBug);%>
                        <h3>Bug information</h3>
                        Name: <input type="text" readonly name = "nameBug" tabindex="1" type="text" value="<%=((String)bug.get(1))%>" >
                        Description: <input type="text" readonly name = "info" tabindex="1" type="text" value="<%=((String)bug.get(5))%>">
                  		Form: <input type="text" readonly name = "form" tabindex="1" type="text" value="<%=((String)bug.get(6))%>">
                  		Status: <input type="text" readonly name = "st" value=<%=((String)bug.get(0))%>>
                   		Date: <input type="text" readonly name = "data" value="<%=((String)bug.get(4))%>">
                   		From: <input type="text" readonly name = "fromwho" tabindex="1" type="text" value="<%=((String)bug.get(2))%>"> 
                   		To: <input type="text" readonly name = "towho" tabindex="1" type="text" value="<%=((String)bug.get(3))%>">
                        <%if (!((String)bug.get(7)).equals("")){%>
                   			Parent:<p class="message"><input type= "submit" name ="bt" value ="<%=((String)bug.get(7))%>"></p> 
               			<%}
                   		ArrayList chidren =bugs.getChildren(nameBug);				
                        if(chidren.size()!=0){%>
                        	Children: 
                            <%for(int i=0; i<chidren.size(); i++){ %>
                        		<p class="message"><input type= "submit" name ="bt" value ="<%=chidren.get(i)%>"></p>
                        	<%}
                        }
            		}
        			catch (Exception e) {
           		 		e.printStackTrace();
        			} 
        			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/LoginServlet");
        			String user = (String) session.getAttribute("login");
        			boolean hidden = false; 
        			if (user != null)
          				hidden = false; 
        			else 
          				hidden = true;
			  		if (hidden == false){%>
        				<input type= "submit" id = "search"  name ="bt"  onClick="alert('<%=nameBug%> and nested bugs deleted')" value ="Delete"/>
            		   	<input type= "submit" id = "search"  name ="bt" value ="Change"/>
      					<%} 
			  		else {%>    
      					<input type= "submit" id = "search" name ="bt" onClick="test()" value ="Delete" hidden = "<%=hidden%>"/>
      					<input type= "submit" id = "search" name ="bt" value ="Change" hidden = "<%=hidden%>"/>
					<% }%>
				</form>
			</div>
 		</div>
	</body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script src="boot.js" type="text/javacsript"></script>
</html>