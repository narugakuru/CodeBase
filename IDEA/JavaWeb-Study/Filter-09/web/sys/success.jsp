<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/15
  Time: 8:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>登录成功</h1>
<a href="${pageContext.request.contextPath}/s/logout">注销</a>
<%--<%
    Object user_session = request.getSession().getAttribute("USER_SESSION");
    if (user_session==null){
        response.sendRedirect("index.jsp");
//        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
%>--%>
</body>
</html>
