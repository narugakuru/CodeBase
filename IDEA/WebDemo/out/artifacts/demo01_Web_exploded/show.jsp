<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户界面</title>
</head>
<%
    Set alluser = (Set) application.getAttribute("alluser");
    System.out.println(alluser);
%>

<body background="8bitisland.png">
<div style="text-align:center;">
    <h1>在线用户列表：</h1>    <br>
    <%--打印用户列表--%>
    <c:forEach var="user" items="${applicationScope.alluser}">
        <c:out value="${user}"/> <br>
    </c:forEach>
    <hr>

    <h1>私聊消息</h1>
    <pre>
        <%=session.getAttribute("privateChat")%>
</pre>
    <a href="main.jsp">返回群聊</a>
</div>

</body>
</html>