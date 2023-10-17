<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.*" %>
<html>
<body>
<h2>Hello World!</h2>

<%= new Date()%>
<hr>
<%--jsp脚本片段--%>
<%
    int sum = 0;
    for (int i = 0; i < 100; i++) {
        sum += i;
    }
    out.println(sum);
%>

<%
    int x = new Random().nextInt(100);
    out.println(x);
%>
<h2>这是一个jsp脚本</h2>
<%
    for (int i = 0; i < 5; i++) {
        out.println(new Random().nextInt(100));
%>
<hr>
<%
        out.println(i);
    }
%>
<hr>


<%!
    static {
        System.out.println("Loading Servlet!");
    }

    private int globalVar = 114514;

    public void raisei(int Var) {
        System.out.println("进入了Raisei的方法" + Var);
    }

%>

</body>
</html>
