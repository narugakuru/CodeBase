<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/5/3
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Hello
${msg}

<form action="${pageContext.request.contextPath}/restful2" method="post">
    <input name="id">
    <input type="submit" value="提交跳转">
</form>
</body>
</html>
