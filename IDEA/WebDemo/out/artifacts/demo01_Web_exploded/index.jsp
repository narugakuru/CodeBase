<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="canonical" href="https://getbootstrap.com/docs/3.4/examples/signin/">
    <title>登录界面</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.20/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.20/assets/css/ie10-viewport-bug-workaround.css"
          rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.20/examples/signin/signin.css"
          rel="stylesheet">
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.20/assets/js/ie8-responsive-file-warning.js"></script>
    <![endif]-->

</head>

<body background="8bitisland.png">

<div class="container">

    <form class="form-signin" action="/login" method="get">
        <h2 class="form-signin-heading">Please Login</h2>

        <label for="username" class="sr-only">User Name</label>
        <input type="text" id="username" name="username" class="form-control" autofocus="">

        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" name="password" class="form-control" >

        <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
    </form>

</div>
</body>
</html>