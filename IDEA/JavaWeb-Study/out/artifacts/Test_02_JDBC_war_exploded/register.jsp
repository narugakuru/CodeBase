<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/13
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$注册Register$</title>
</head>
<body>
<form action="doRegister.jsp" method="post">
    ID号:<input type="number" name="sId"> <br>
    姓名：<input type="text" name="sName"/> <br>
    年龄：<input type="number" name="sAge"> <br>
    性别：<input type="radio" name="sSex" value="男">男
    <input type="radio" name="sSex" value="女">女
    <br>
    <input type="submit" value="注册">
</form>
</body>
</html>
