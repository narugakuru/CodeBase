<%@ page import="entity.Student" %>
<%@ page import="util.DBStudent" %><%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/14
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //从查询页面超链接携带过来的Id
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    String Id = request.getParameter("Id");
    Student stu = DBStudent.selectId(Id);
%>
<h2>修改页面</h2>
<form ACTION="doUpdate.jsp" METHOD="post">
    <label>
        ID号:<input type="number" name="sId" readonly="readonly" value="<%=stu.getId()%>"> <br>
        姓名：<input type="text" name="sName" value="<%=stu.getName()%>"/> <br>
        年龄：<input type="number" name="sAge" value="<%=stu.getAge()%>"> <br>
        性别：<input type="radio" name="sSex" value="男" checked="<%=stu.getSex().equals("男")%>">男
        <input type="radio" name="sSex" value="女" checked="<%=stu.getSex().equals("女")%>">女
        <br><input type="submit" value="修改">
    </label>
</form>
<%--<label>
    <%
        boolean sex = stu.getSex().equals("男");
        out.print(sex);
        out.print(stu.toString());
    %>
        性别：<input type="radio" name="sSex" value="男" checked="<%=stu.getSex().equals("男")%>">男
        <input type="radio" name="sSex" value="女" checked="<%=stu.getSex().equals("女")%>">女
</label>--%>
</body>
</html>
