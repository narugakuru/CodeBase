<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/8
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>if</title>
</head>
<body>
<form action="coreif.jsp" method="get">
    <h1>if测试</h1>
    <input type="text" name="username" value="${param.username}">
    <input type="submit" value="登录">
</form>
<%--<%
    if (request.getParameter("username").equals("admin")) {
        out.print("欢迎管理员");
    }
%>--%>
<c:if test="${param.username=='admin'}" var="isAdmin" scope="session">
    <c:out value="欢迎管理员"/>
</c:if>



</body>
</html>
