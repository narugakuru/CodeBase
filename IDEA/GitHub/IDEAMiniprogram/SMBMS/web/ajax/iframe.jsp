<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/4/21
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript">
    window.onload = function Ctime() {
        var myDate = new Date();
        document.getElementById("currentTime").innerText = myDate.getTime();
    };

    function loadPage() {
        var targetURL = document.getElementById("url").valueOf();
        document.getElementById("iframePosition").src = targetURL;
    }
</script>


<div>
    <p>输入要加载的地址： <span id="currentTime"></span></p>
    <p>
        <input type="text" id="url" value="http://www.baidu.com">
        <input type="submit" value="提交" onclick="">
    </p>
</div>
<div>
    <p>加载的页面:</p>
    <iframe id="iframePosition" style="width: 100%;height: 500px" src="">

    </iframe>

</div>
</body>
</html>
