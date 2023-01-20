<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/4
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP1</title>
</head>
<body>

<%
    String Name = (String) request.getParameter("myName");
    System.out.println(Name);
%>
    <h1>myName:${Name}</h1>
    <h1>myName:<%=Name%></h1>

<hr>

<h1>Name:<%=request.getParameter("myName")%></h1>


</body>
</html>
