<%@ page import="java.io.PrintWriter" %>
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
    <title>$Title$</title>
  </head>
  <body>
  <form action="doLogin.jsp" name="loginForm">
    <div id="loginFormMain">
      用户名<input type="text" name="username" value=""><br>
      密码<input type="password" name="password" value=""><br>
      <input type="submit" value="登录">
      <input type="reset" value="重置">
    </div>
  </form>

<%
  Cookie[] cookies = request.getCookies();

  if (cookies!=null){
    response.sendRedirect("login_success.jsp");
  }
  //给客户端一个新Cookie
  Cookie cookie = new Cookie("login","noPwd");
  cookie.setMaxAge(5*60);
  response.addCookie(cookie);
%>
  </body>
</html>
