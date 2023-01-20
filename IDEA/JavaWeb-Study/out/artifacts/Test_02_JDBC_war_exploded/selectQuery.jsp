<%@ page import="util.DBStudent" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/13
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.lang.*" %>
<%@ page import="util.DBStudent" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.sql.*" %>
<html>
<head>
    <title>查询</title>
</head>
<body>
<table border="1" style="align-items: center">
    <tr>
        <td>学号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
        <td>操作一</td>
        <td>操作二</td>
    </tr>
    <%

        try {
            Connection connection = DBStudent.getConnection();
            String sql = "select * from student";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                String Name = resultSet.getString("name");
                int Age = resultSet.getInt("age");
                String Sex = resultSet.getString("sex");

                out.print("<tr>");
                out.print("<td>" + Id + "</td>");
                out.print("<td>" + Name + "</td>");
                out.print("<td>" + Age + "</td>");
                out.print("<td>" + Sex + "</td>");
                out.print("<td><a href='update.jsp?Id=" + Id + "'>修改</a></td>");
                out.print("<td><a href='delete.jsp?Id=" + Id + "'>删除</a></td>");
                out.print("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        out.print("<br>" + Id + "\t\t" + Name + "\t\t" + Age + "\t\t" + Sex);
//        System.out.println(Id + "\t" + Name + "\t" + Age + "\t" + Sex);

    %>
</table>
</body>
</html>
