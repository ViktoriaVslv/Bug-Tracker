package controller;
import java.io.IOException;
import java.util.ArrayList;
import model.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;

public class Table extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
    	try {
    		String name = request.getParameter("poisk");
    		if("Search".equals(request.getParameter("bt"))){
    			HttpSession session1 = request.getSession();
    			session1.setAttribute("name", name);
    			request.getRequestDispatcher("table.jsp").include(request, response);
    		}
    		if("Add bug/feature".equals(request.getParameter("bt")))
    			response.sendRedirect("bug.jsp");
    		else {
    			BDBugs bugs = new BDBugs();
    			ArrayList all =bugs.getAllBugs1();
                for(int i=0; i<all.size(); i++) {
                	String nameBug = (String)all.get(i);
                	if(nameBug.equals(request.getParameter("bt"))) {
                		HttpSession session1 = request.getSession();
                		session1.setAttribute("nameBug", nameBug);
                		request.getRequestDispatcher("infoBug.jsp").include(request, response);
                		break;
                	}
                }
                bugs.close();
    		}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
