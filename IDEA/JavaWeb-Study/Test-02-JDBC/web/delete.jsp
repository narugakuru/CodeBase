<%@ page import="util.DBStudent" %><%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/14
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>删除页面</h2>
<%
    request.setCharacterEncoding("utf-8");
    //根据学号去查询对应的学生信息
    String id = request.getParameter("Id");
    int result= DBStudent.delete(id);
    if(result==0){
        request.getRequestDispatcher("selectQuery.jsp").forward(request, response);
    }else{
        out.println("<script>alert('删除失败')</script>");
    }
%>
</body>
</html>
