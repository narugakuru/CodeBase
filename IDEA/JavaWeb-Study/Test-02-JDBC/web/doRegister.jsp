<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/13
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.util.*" %>
<%@ page import="entity.Student" %>
<%@ page import="util.DBStudent" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        request.setCharacterEncoding("utf-8");

        int Id = Integer.parseInt(request.getParameter("sId"));
        String Name = request.getParameter("sName");
        int Age = Integer.parseInt(request.getParameter("sAge"));
        String Sex = request.getParameter("sSex");

        Student student = new Student(Id,Name,Age,Sex);

        int result = DBStudent.register(student);//将数据传入处理

        /*if(result>0){
            request.getRequestDispatcher("query.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }*/
        System.out.println("数据以传入DBStudent");
    %>

<h1>数据处理ing</h1>
</body>
</html>
