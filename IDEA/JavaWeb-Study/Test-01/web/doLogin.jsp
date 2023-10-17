<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/8
  Time: 8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8"); //防止中文乱码
    String path = request.getContextPath();
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    //如果用户和密码都等于admin,则登录成功
    if ("admin".equals(username) && "root".equals(password)) {
//        转发到login_success.jsp
        request.getRequestDispatcher("login_success.jsp").forward(request, response);
    } else {
        //重定向到login_failure.jsp
        response.sendRedirect("login_failure.jsp");
    }
%>
</body>
</html>
