/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-01-09 08:49:00 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.PrintWriter;
import javax.servlet.*;
import java.util.*;
import java.sql.*;
import model.BDBugs;
import model.BDUsers;
import javax.servlet.http.*;

public final class table_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\t<head>\r\n");
      out.write("    \t<meta charset=\"UTF-8\">\r\n");
      out.write("    \t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    \t<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n");
      out.write("    \t<link rel= \"stylesheet\" href=\"table.css\" />\r\n");
      out.write("    \t<title>Tracker Bug</title>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t<body> \r\n");
      out.write("        <div class=\"table-page\">\r\n");
      out.write("        \t<div class=\"form\">");
RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/LoginServlet");
        		String user = (String) session.getAttribute("login");
        		boolean hidden = false; 
        		if (user != null)
          			hidden = false; 
        		else 
          			hidden = true;
        
        		if(hidden == false){
      out.write("\r\n");
      out.write("\t\t\t\t\t<form class=\"message\" action = \"LogoutServlet\" method=\"post\">\r\n");
      out.write("\t\t\t\t\t\t<input type= \"submit\" name =\"bt\" value =\"Logout\"/>\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t");
} 
      out.write("\r\n");
      out.write("                <form class=\"table-form\" action = \"Table\" method=\"post\">\r\n");
      out.write("                  \t<div class=\"d3\"><h3><span>Bug Tracker</span></h3></div>\r\n");
      out.write("      \t\t\t\t\t");
if (hidden == false){
      out.write("\r\n");
      out.write("      \t\t\t\t\t\t<div class=\"d4\"><h3>Hello, ");
      out.print(user);
      out.write("</h3></div>\r\n");
      out.write("        \t\t\t\t\t<input type= \"submit\" id = \"bug\" name =\"bt\" value =\"Add bug/feature\"/>\r\n");
      out.write("        \r\n");
      out.write("      \t\t\t\t\t");
} 
      					else {
      out.write("    \r\n");
      out.write("      \t\t\t\t\t\t<input type= \"submit\" id = \"bug\" value =\"Add bug/feature\" hidden = \"");
      out.print(hidden);
      out.write("\"/>\r\n");
      out.write("      \t\t\t\t\t");
}
      out.write(" \r\n");
      out.write("      \t\t\t\t\t<input type=\"text\" name = \"poisk\" placeholder=\"Name of bug\" value/> \r\n");
      out.write("        \t\t\t\t<input type= \"submit\" id = \"search\"  name =\"bt\" value =\"Search\"/>\r\n");
      out.write("     \r\n");
      out.write("\t\t\t");
BDBugs bugs = new BDBugs();
            try {
      out.write("\r\n");
      out.write("            \t<table>\r\n");
      out.write("\t\t\t\t\t<tr><th>Name</th><th>Description</th><th>Form</th><th>Status</th><th>Date</th>");
 
					RequestDispatcher dispatcher1 = this.getServletContext().getRequestDispatcher("/Table");
					String nameBug= (String) session.getAttribute("name");
					session.removeAttribute("name");
					if(nameBug==null){
						ArrayList b = bugs.getSortedBug();
						for(int i=0; i<b.size(); i++){
							ArrayList all =bugs.getAll((String)b.get(i));
							String info = (String)all.get(5);
		                 	if(info.length()>=30)
		                 		info=info.substring(0,30)+"...";
      out.write("\r\n");
      out.write("\t\t                 \t<tr><td><p class=\"message\"><input type= \"submit\" name =\"bt\"  value =\"");
      out.print(((String)all.get(1)));
      out.write("\"></p> </td><td>");
      out.print(info);
      out.write("</td><td>");
      out.print(((String)all.get(6)));
      out.write("</td><td>");
      out.print(((String)all.get(0)));
      out.write("</td><td>");
      out.print(((String)all.get(4)));
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t");
}
					}
					else{
						ArrayList b = bugs.getSortedBug();
						for(int i=0; i<b.size(); i++){
							if(((String)b.get(i)).startsWith(nameBug)){
								ArrayList all =bugs.getAll((String)b.get(i));
								String info = (String)all.get(5);
		                 		if(info.length()>=30)
		                 			info=info.substring(0,30)+"...";
      out.write("\r\n");
      out.write("\t\t                 \t\t<tr><td><p class=\"message\"><input type= \"submit\" name =\"bt\"  value =\"");
      out.print(((String)all.get(1)));
      out.write("\"></p> </td><td>");
      out.print(info);
      out.write("</td><td>");
      out.print(((String)all.get(6)));
      out.write("</td><td>");
      out.print(((String)all.get(0)));
      out.write("</td><td>");
      out.print(((String)all.get(4)));
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t");
}
	                   }
	               }
      out.write("\r\n");
      out.write("\t\t\t\t</table> \r\n");
      out.write("\t\t\t");
}
        	catch (Exception e) {
           		 e.printStackTrace();
        	}
      out.write("\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("        \t</div>\r\n");
      out.write("    \t</div>\r\n");
      out.write("\t</body>\r\n");
      out.write("\t<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t<script src=\"boot.js\" type=\"text/javacsript\"></script>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
