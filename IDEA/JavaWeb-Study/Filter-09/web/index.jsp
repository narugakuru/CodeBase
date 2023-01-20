<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/10
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<h1>当前有<span><%=this.getServletConfig().getServletContext().getAttribute("OnlineCount")%></span>人在线</h1>
<form action="${pageContext.request.contextPath}/s/login">
    <label>
        <input type="text" name="username">
        <input type="submit" value="提交">
    </label>
</form>
</body>
</html>
