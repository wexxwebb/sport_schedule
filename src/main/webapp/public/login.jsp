<!--
  Created by IntelliJ IDEA.
  User: admin
  Date: 16.01.2018
  Time: 17:08
  To change this template use File | Settings | File Templates.
-->
<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>-->
<!--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Расписание спортивных тренировок</title>

    <!-- Bootstrap -->
    <%--<link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet">--%>
    <%--<link href="${pageContext.request.contextPath}/style/css/bootstrap.min.css" rel="stylesheet">--%>
    <link href="${pageContext.request.contextPath}/style/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-12"><h1 class="h-main">Расписание тренировок</h1></div>
        </div>
        <div class="row" style="margin-top: 125px;">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div style="width: 250px; margin: auto">
                    <form method="post" action="${pageContext.request.contextPath}/login">
                        <div class="log-in-group">
                            <div class="form-group" style="display: inline-block">
                                <input type="login" name="login" class="form-control iput-auth" placeholder="Имя пользователя">
                            </div>
                            <div class="form-group" style="display: inline-block">
                                <input type="password" name="password" class="form-control iput-auth" placeholder="Пароль">
                                <span class="reg-error">${requestScope.authError}</span>
                            </div>
                            <div class="inline-block">
                                <button type="submit" class="btn btn-primary log-in-btn">Войти</button>
                                <input class="check" type="checkbox"> Запомнить меня
                            </div>
                            <div style="margin-top: 15px;">
                                <a href="${pageContext.request.contextPath}/register">Регистрация</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/style/js/bootstrap.min.js"></script>
</body>
</html>
