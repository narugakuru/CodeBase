<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/8
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>bean</title>
</head>
<body>
<%--不必new对象，直接调用--%>
<jsp:useBean id="p" class="pojo.people" scope="page">
    <jsp:setProperty name="p" property="id" value="123"/>
    <jsp:setProperty name="p" property="name" value="raisei"/>
</jsp:useBean>
    <jsp:getProperty name="p" property="id"/>
    <jsp:getProperty name="p" property="name"/>
</body>
</html>
