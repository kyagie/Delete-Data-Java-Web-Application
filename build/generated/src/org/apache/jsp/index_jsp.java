package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


                    public class User {

                        String URL = "jdbc:mysql://localhost:3306/traindatabase";
                        String USERNAME = "root";
                        String PASSWORD = "";

                        Connection con = null;
                        PreparedStatement selectUsers = null;
                        PreparedStatement deleteUsers = null;
                        ResultSet rs = null;

                        public User() {
                            try {
                                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                                selectUsers = con.prepareStatement(
                                        "SELECT user_id, u_fname, u_lname FROM user");

                                deleteUsers = con.prepareStatement(
                                        "DELETE FROM user WHERE user_id = ?");

                            } catch (SQLException e) {
                                System.out.println(e);
                            }
                        }

                        public ResultSet getUsers() {
                            try {
                                rs = selectUsers.executeQuery();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            return rs;
                        }

                        public int deleteUser(Integer id) {
                            int result = 0;
                            try {
                                deleteUsers.setInt(1, id);
                                result = deleteUsers.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();

                            }
                            return result;
                        }
                    }
                
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
 Class.forName("com.mysql.jdbc.Driver");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\">\n");
      out.write("        <title>Deleting From a database</title>\n");
      out.write("    </head>\n");
      out.write("    <div id=\"container\">\n");
      out.write("        <body onload=\"displayResults()\">\n");
      out.write("            <div id=\"header\">\n");
      out.write("                <h1>Deleting From a database</h1>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"navigation\">\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"index.jsp\">Home</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"content\">\n");
      out.write("                ");
      out.write("\n");
      out.write("                ");

                    int result = 0;

                    User user = new User();
                    ResultSet users = user.getUsers();

                    Integer user_id = new Integer(0);

                    if (request.getParameter("submit") != null) {
                        user_id = Integer.parseInt(request.getParameter("user"));
                        result = user.deleteUser(user_id);
                    }

                
      out.write("\n");
      out.write("                <form name=\"myForm\" action=\"index.jsp\" method=\"POST\">\n");
      out.write("                    <table border=\"0\">\n");
      out.write("                        <tbody>\n");
      out.write("                            <tr>\n");
      out.write("                                <td>Passenger Name</td>\n");
      out.write("                                <td><select name=\"user\">\n");
      out.write("                                        ");
while (users.next()) {
      out.write("\n");
      out.write("                                        <option value=\"");
      out.print( users.getInt("user_id"));
      out.write("\"> ");
      out.print(users.getString("u_fname"));
      out.print(users.getString("u_lname"));
      out.write("  </option>\n");
      out.write("                                        ");
 }
      out.write("\n");
      out.write("                                    </select></td>\n");
      out.write("                            </tr>\n");
      out.write("                        </tbody>\n");
      out.write("                    </table>\n");
      out.write("                    <input type=\"hidden\" name=\"hidden\" value=\"");
      out.print(result);
      out.write("\" />        \n");
      out.write("                    <input type=\"submit\" value=\"Submit\" name=\"submit\" />\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("            <SCRIPT lang=\"JavaScript\">\n");
      out.write("                <!--\n");
      out.write("    function displayResults() {\n");
      out.write("\n");
      out.write("                    if (document.myForm.hidden.value == 1) {\n");
      out.write("                        alert(\"Data Was Deleted\");\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("                //-- >\n");
      out.write("            </SCRIPT>\n");
      out.write("        </body>\n");
      out.write("        <div id=\"footer\">\n");
      out.write("            Copyright @ Here website name\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
