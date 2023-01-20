<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/8
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>for</title>
</head>
<body>
<%
    ArrayList<String> array = new ArrayList<>();
    array.add(0,"fq");
    array.add(1,"no");
    array.add(2,"elem");

    request.setAttribute("list",array);
%>
<c:forEach var="v" items="${list}">
    <c:out value="${v}"/> <br>
</c:forEach>

<hr>
<%--begin和end都是下标--%>
<c:forEach var="vv" items="${list}" begin="1" end="3" step="2" varStatus="">
    <c:out value="${vv}"/> <br>
</c:forEach>

</body>
</html>
