<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<br>
<div style="text-align: center">
<%--    以post方式提交表单--%>
    <form action="${pageContext.request.contextPath}/login" method="get">
        用户名：<input type="text" name="username"><br>
        密码：<input type="password" name="password"> <br>
        <input type="checkbox" name="hobbys" value="raisei">raisei
        <input type="checkbox" name="hobbys" value="dio">dio
        <input type="checkbox" name="hobbys" value="jojo">jojo <br>
        <input type="submit">
    </form>
</div>
</body>
</html>
