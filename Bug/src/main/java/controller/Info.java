package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import model.BDBugs;


public class Info extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType("text/html");
    try {
    	BDBugs bugs = new BDBugs();

            if("Delete".equals(request.getParameter("bt"))){
            	String name = request.getParameter("nameBug");
            	ArrayList parents = bugs.newL1(name);
            	for(int i=0; i<parents.size(); i++) {
            		bugs.deleteBugs((String)parents.get(i));
            	}
               	request.getRequestDispatcher("table.jsp").include(request, response);
            }
            if("Change".equals(request.getParameter("bt"))){
            	HttpSession session1 = request.getSession();
            	String  nameBug=request.getParameter("nameBug");
            	session1.setAttribute("change", nameBug);
            	request.getRequestDispatcher("bug.jsp").include(request, response);
            }
           
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
    catch (Exception e) {
        e.printStackTrace();
    }
}
}