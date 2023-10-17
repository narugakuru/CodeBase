<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/8
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>when</title>
</head>
<body>
<c:set var="score" value="88"/>
<c:choose>
    <c:when test="${score>=90}">
        <h1>${score}优秀</h1>
    </c:when>
    <c:when test="${score>=80}">
        <h1>${score}还行</h1>
    </c:when>
</c:choose>
</body>
</html>
