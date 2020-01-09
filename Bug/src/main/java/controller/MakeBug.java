package controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;

public class MakeBug extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
    	String name = request.getParameter("name");
    	String description = request.getParameter("description");
    	String towho = request.getParameter("towho");
    	String parent = request.getParameter("parent");
    	String status = request.getParameter("list1");
    	String st;
    	if (status.equals("Open")) 
    		st = "True";
    	else
    		st = "False";
    	String status2 = request.getParameter("list");
    	String bf;
    	if (status2.equals("Bug"))
    		bf = "True";
    	else
    		bf = "False";
    	HttpSession session = request.getSession(false);
    	String log = (String)session.getAttribute("login");
    	String date =new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
    	
        if (name.equals("")||towho.equals("")) {
        	HttpSession session1 = request.getSession();
     	    session1.setAttribute("alert", "noname");
     	    request.getRequestDispatcher("bug.jsp").include(request, response);
        }
        else {
        	try {	
        		BDBugs bugs = new BDBugs();
        		if("Save new bug".equals(request.getParameter("m"))) {
        			boolean find = bugs.find(name);
        			if (!find){
       					bugs.insert(st, name, log, towho, date, description, bf, parent);
       					request.getRequestDispatcher("table.jsp").include(request, response);
       				}
       				else {	
       					HttpSession session1 = request.getSession();
       		     	    session1.setAttribute("alert", "ex");
       		     	    request.getRequestDispatcher("bug.jsp").include(request, response);
       				}
       			}
        		if("Save".equals(request.getParameter("m"))) {
        			String ch = (String)session.getAttribute("change");
        			session.removeAttribute("change");
        			boolean b=true;
       				if(st.equals("False")) {
       					boolean check = bugs.checkSt(ch, name);
       					if(check) {
       						b=false;
       						HttpSession session1 = request.getSession();
       						session1.setAttribute("change", ch);
       			     	    session1.setAttribute("alert", "close");
       			     	    request.getRequestDispatcher("bug.jsp").include(request, response);
       					}	
        			}
        			if(b){
        	       		bugs.update(ch,st,name,towho,date,description,bf,parent);
        	       		request.getRequestDispatcher("table.jsp").include(request, response);
        			}
        			bugs.close();
        		} 
       		}
       		catch (Exception e) {
       			e.printStackTrace();
       		}
       	}
    }
}
