<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Raisei
  Date: 2021/5/13
  Time: 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.4/favicon.ico">
    <link rel="canonical" href="https://getbootstrap.com/docs/3.4/examples/dashboard/">

    <title>User View</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.4/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.4/assets/css/ie10-viewport-bug-workaround.css"
          rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.4/examples/dashboard/dashboard.css"
          rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.4/assets/js/ie8-responsive-file-warning.js"></script>
    <![endif]-->
    <script src="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.4/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body >


<%--?????????????????????--%>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">?????????????????????</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/user/logout">??????</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <label>
                    <input type="text" class="form-control" placeholder="${user.account},??????">
                </label>
            </form>
        </div>
    </div>
</nav>

<div class="container">
    <%--???????????????--%>
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="${pageContext.request.contextPath}/user/reUser?account=${user.account}">????????????
                    <span class="sr-only">(current)</span></a></li>
                <li><a href="${pageContext.request.contextPath}/user/userMessage">????????????</a></li>
                <li><a href="${pageContext.request.contextPath}/itinerary/queryAll?userId=${user.userId}">??????????????????</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/user/logout">??????</a></li>
            </ul>
        </div>
    </div>
    <%--???????????????--%>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <c:if test="${orderIds!=null}">
            <h1 class="page-header">us`Team</h1>
            <%--??????????????????--%>
            <div class="row placeholders">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="../../../image/dio.jpg"
                         width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>One</h4>
                    <span class="text-muted">?????????io???</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="../../../image/jojo.jpg"
                         width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>Two</h4>
                    <span class="text-muted">JOJO</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="../../../image/yoshikake.jpg"
                         width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>There</h4>
                    <span class="text-muted">Something else</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="../../../image/kotowaru.jpg"
                         width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>Four</h4>
                    <span class="text-muted">Som ething else</span>
                </div>
            </div>
        </c:if>
        <%--????????????--%>
        <c:if test="${orderIds!=null}">
            <h2 class="sub-header">??????????????????</h2>
        </c:if>
        <c:if test="${orderIds==null}">
            <c:if test="${itineraries!=null}">
                <h2 class="sub-header">?????????????????????????????????</h2>
            </c:if>
            <c:if test="${itineraries==null}">
                <h2 class="sub-header">??????????????????</h2>
            </c:if>
        </c:if>
        <c:if test="${itineraries!=null}">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>?????????</th>
                        <th>?????????</th>
                        <th>??????</th>
                        <th>??????</th>
                        <c:if test="${orderIds!=null}">
                            <td>?????????</td>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${itineraries}" var="itinerary" varStatus="status">
                        <tr>
                            <th scope="row">${status.index+1}</th>
                            <td>${itinerary.origin}</td>
                            <td>${itinerary.destination}</td>
                            <td>${itinerary.cost}</td>
                            <td>${itinerary.date}</td>
                            <c:if test="${orderIds!=null}">
                                <td>${orderIds[status.index]}</td>
                            </c:if>
                            <td>
                                <a href="${pageContext.request.contextPath}/order/delete?orderId=${orderIds[status.index]}">????????????</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${itineraries==null}">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>??????ID</td>
                        <td>?????????</td>
                        <td>??????</td>
                        <td>??????</td>
                        <td>??????</td>
                        <td>?????????</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.name}</td>
                        <td>${user.account}</td>
                        <td>${user.age}</td>
                        <td>${user.sex}</td>
                        <td>${user.phone}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </c:if>

    </div>
</div>


</body>

</html>
