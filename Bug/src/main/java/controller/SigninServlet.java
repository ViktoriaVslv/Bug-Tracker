package controller;
import java.io.*;
import model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;

public class SigninServlet extends HttpServlet {
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        response.setContentType("text/html");
        String logins = request.getParameter("login");
        String pass = request.getParameter("password");
        String pass1 = request.getParameter("password_two");
        
        if(logins.equals("") || pass.equals("") || pass1.equals("")) {
        	HttpSession session1 = request.getSession();
     	    session1.setAttribute("alert", "noname");
     	    request.getRequestDispatcher("signup.jsp").include(request, response);
        }
        else {	
        	if(pass.equals(pass1)){
        		try {
        				BDUsers users = new BDUsers();
        				boolean find = users.find(logins);
        				if (!find){
        					HttpSession session = request.getSession();
        					session.setAttribute("login", logins);
        					users.insert(logins, pass);
        					response.sendRedirect("table.jsp");	
        				}
        				else {	
        					HttpSession session1 = request.getSession();
        		     	    session1.setAttribute("alert", "loginallowed");
        		     	    request.getRequestDispatcher("signup.jsp").include(request, response);
        				}
        				users.close();
        		}
        		catch (Exception e) {
        			e.printStackTrace();
        		}
        	} 
        	else {
        		HttpSession session1 = request.getSession();
         	    session1.setAttribute("alert", "nopass");
         	    request.getRequestDispatcher("signup.jsp").include(request, response);
        	}
        }
    }
}
