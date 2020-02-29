<%@page import="java.sql.*" %>
<% Class.forName("com.mysql.jdbc.Driver");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css">
        <title>Deleting From a database</title>
    </head>
    <div id="container">
        <body onload="displayResults()">
            <div id="header">
                <h1>Deleting From a database</h1>
            </div>
            <div id="navigation">
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                </ul>
            </div>
            <div id="content">
                <%!
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
                %>
                <%
                    int result = 0;

                    User user = new User();
                    ResultSet users = user.getUsers();

                    Integer user_id = new Integer(0);

                    if (request.getParameter("submit") != null) {
                        user_id = Integer.parseInt(request.getParameter("user"));
                        result = user.deleteUser(user_id);
                    }

                %>
                <form name="myForm" action="index.jsp" method="POST">
                    <table border="0">
                        <tbody>
                            <tr>
                                <td>Passenger Name</td>
                                <td><select name="user">
                                        <%while (users.next()) {%>
                                        <option value="<%= users.getInt("user_id")%>"> <%=users.getString("u_fname")%><%=users.getString("u_lname")%>  </option>
                                        <% }%>
                                    </select></td>
                            </tr>
                        </tbody>
                    </table>
                    <input type="hidden" name="hidden" value="<%=result%>" />        
                    <input type="submit" value="Submit" name="submit" />
                </form>
            </div>
            <SCRIPT lang="JavaScript">
                <!--
    function displayResults() {

                    if (document.myForm.hidden.value == 1) {
                        alert("Data Was Deleted");
                    }
                }
                //-- >
            </SCRIPT>
        </body>
        <div id="footer">
            Copyright @ Here website name
        </div>
    </div>
</html>
