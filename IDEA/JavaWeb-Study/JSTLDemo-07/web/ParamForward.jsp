<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/4
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <title>forward</title>
</head>
<body>
<%-- 携带参数转发--%>
    <jsp:forward page="jsp1.jsp">
        <jsp:param name="myName" value="raisei"/>
    </jsp:forward>

</body>
</html>
