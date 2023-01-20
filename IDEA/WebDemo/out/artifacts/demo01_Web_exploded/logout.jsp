<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/12/12
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    request.getSession().invalidate();
%>
<body>
<h1>用户已下线</h1>
<a href="index.jsp">返回登录界面</a>
</body>
</html>
