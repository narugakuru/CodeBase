<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/12/11
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <title>Web聊天室</title>
</head>
<%
    Set alluser = (Set) application.getAttribute("alluser");
    System.out.println("main.jsp:alluser==>" + alluser.toString());
%>
<body background="8bitisland.png">

<div style="text-align:center;">
    <h1>在线用户列表：</h1>    <br>
    <%--打印用户列表--%>
    <c:forEach var="user" items="${applicationScope.alluser}">
        <c:out value="${user}"/> <br>
    </c:forEach>
    <hr>
    <form action="${pageContext.request.contextPath}/message.do" method="post">
        <h1> Web聊天室</h1>
        <br>
        <h2> 群聊消息:</h2> <br>
        <pre> <%=session.getAttribute("chatRecord")%></pre>
        <br>

        <h2>私聊消息:</h2>  <br>
        <pre> <%=session.getAttribute("privateChat")%></pre>
        <br>
        请输入私聊对象的名字(不输入则是群聊）：
        <label>
            <input type="text" name="chatName">
            <br>
            请输入聊天信息：
            <input type="text" name="inputChat">
            <input type="submit" value="提交">
        </label>
    </form>
    <a href="logout.jsp">下线</a>
    <a href="show.jsp">仅查看私聊消息</a>
</div>
</body>
</html>