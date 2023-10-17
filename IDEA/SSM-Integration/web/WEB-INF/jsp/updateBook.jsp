
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

</head>
<body>

<div class="container">
    <form class="form-signin" action="${pageContext.request.contextPath}/book/doUpdateBook" method="post">
        <h2 class="form-signin-heading">修改书籍信息</h2>

        <input type="hidden" value="${books.bookID}" name="bookID">

        <label>bookName</label>
        <input type="text" name="bookName" class="form-control" required="" autofocus="" value="${books.bookName}">

        <label>bookCounts</label>
        <input type="text" name="bookCounts" class="form-control" required="" value="${books.bookCounts}">

        <label>detail</label>
        <input type="text" name="detail" class="form-control" required="" value="${books.detail}">

        <button class="btn btn-lg btn-primary btn-block" type="submit">添加</button>
    </form>
</div>

</body>
</html>
