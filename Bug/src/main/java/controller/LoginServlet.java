package controller;
import java.io.*;
import model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;


public class LoginServlet extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setContentType("text/html");
       String logins = request.getParameter("login");
       String pass = request.getParameter("password");
      
       if(logins.equals("") || pass.equals("")) {
    	   HttpSession session1 = request.getSession();
    	   session1.setAttribute("alert", "noname");
    	   request.getRequestDispatcher("login.jsp").include(request, response);
       }
       else {	
    	   try {	
    		   BDUsers users = new BDUsers();
    		   boolean find = users.auth(logins, pass);
    		   if (!find){
    			   HttpSession session1 = request.getSession();
    	    	   session1.setAttribute("alert", "nosuchuser");
    	    	   request.getRequestDispatcher("login.jsp").include(request, response);
            	}
				else {
					HttpSession session = request.getSession();
    				session.setAttribute("login", logins);
    				request.getRequestDispatcher("table.jsp").include(request, response);
				} 			
    	   users.close();
    	   } 
    	   catch (Exception e) {
    		   e.printStackTrace();
    	   }
       }
	}
}
