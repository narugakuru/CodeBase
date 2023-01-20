<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/4
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
    <%
        System.out.println("进入pageDemo1");
        //取出值
        String name1 = (String) pageContext.findAttribute("name1");
        String name2 = (String) pageContext.findAttribute("name2");
        String name3 = (String) pageContext.findAttribute("name3");
        String name4 = (String) pageContext.findAttribute("name4");
    %>
    <%--EL表达式 ￥{} 自动过滤null值--%>

    <h1>${name1}</h1>
    <h1>${name2}</h1>
    <h1>${name3}</h1>
    <h1>${name4}</h1>
<%--        <h1>${name5}</h1>--%>


    </body>
</html>
