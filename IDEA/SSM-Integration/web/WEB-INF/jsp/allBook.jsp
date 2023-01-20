<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/5/6
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/5.0.0-beta3/css/bootstrap-grid.css"
          rel="stylesheet">
</head>
<body>
<div class="container">

    <div class="row">

        <div class="col-md-12 column">
            <div class="page-header">
                <smell>书籍列表 ———————— 显示所有书籍</smell>
            </div>
        </div>

        <div class="col-md-12">
            <p class="lead">
                <a href="${pageContext.request.contextPath}/book/toAddBook" class="btn btn-primary">新增书籍</a>
            </p>
        </div>
    </div>


        <table class="table">
            <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>数量</th>
                <th>详情</th>
            </tr>
            </thead>
            <%--            从list中遍历--%>
            <tbody class="d-md-table">
            <c:forEach var="book" items="${list}">
                <tr>
                    <td>${book.bookID}</td>
                    <td>${book.bookName}</td>
                    <td>${book.bookCounts}</td>
                    <td>${book.detail}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/book/toUpdateBook?id=${book.bookID}">修改</a>
                        &nbsp;|&nbsp;
                        <a href="${pageContext.request.contextPath}/book/doDeleteBook?id=${book.bookID}">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
</div>

</body>
</html>
