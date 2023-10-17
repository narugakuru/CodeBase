<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/14
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.Student" %>
<%@ page import="util.DBStudent" %>
<html>
<head>
    <title>执行修改操作</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    int Id = Integer.parseInt(request.getParameter("sId"));
    String Name = request.getParameter("sName");
    int Age = Integer.parseInt(request.getParameter("sAge"));
    String Sex = request.getParameter("sSex");

    Student student = new Student(Id, Name, Age, Sex);

    int result = DBStudent.update(student);
    out.print("信息已修改");
%>

<form action="selectQuery.jsp">
    <input type="submit" name="back" value="返回">
</form>

</body>
</html>
