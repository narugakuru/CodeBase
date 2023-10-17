<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/4
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>object</title>
</head>
<body>
<%
    //作用域，由低到高
    pageContext.setAttribute("name1","raisei1");//保存的数据只在一个页面有效
    request.setAttribute("name2","raisei2");//保存的数据只在一个请求有效，请求转发会携带数据
    session.setAttribute("name3","raisei3");//保存的数据只在一个会话，从打开到关闭浏览器
    application.setAttribute("name4","raisei4");//保存的数据只在一个服务器
%>

<%--脚本片段的代码会原封不动生成到****_jsp.java--%>
<%
    //取出值
    String name1 = (String) pageContext.findAttribute("name1");
    String name2 = (String) pageContext.findAttribute("name2");
    String name3 = (String) pageContext.findAttribute("name3");
    String name4 = (String) pageContext.findAttribute("name4");
    System.out.println("以进入object");
    pageContext.forward("pageDemo1.jsp");
%>

    <%--EL表达式 ￥{} 自动过滤null值--%>
    <h1>${name1}</h1>
    <h1>${name2}</h1>
    <h1>${name3}</h1>
    <h1>${name4}</h1>

</body>
</html>
