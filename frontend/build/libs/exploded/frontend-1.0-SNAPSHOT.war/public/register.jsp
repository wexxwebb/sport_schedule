<%--suppress ALL --%>
<!--
  Created by IntelliJ IDEA.
  User: admin
  Date: 16.01.2018
  Time: 17:08
  To change this template use File | Settings | File Templates.
-->
<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>-->
<!--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Регистрация</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/style/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/style/datepicker/css/bootstrap-datepicker.css" rel="stylesheet"/>


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
            <div class="col-md-12"><h2 class="h-main">Регистрация</h2></div>
        </div>
        <div class="row" style="margin-top: 40px;">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div style="width: 250px; margin: auto">
                    <form method="post" action="${pageContext.request.contextPath}/register">
                        <div class="log-in-group">
                            <div class="form-group" style="display: inline-block">
                                <input name="login" type="text" class="form-control iput-auth" placeholder="Имя пользователя" value="${requestScope.login}">
                                <span class="reg-error">${loginError}</span>
                            </div>
                            <div class="form-group" style="display: inline-block">
                                <input name="password" type="password" class="form-control iput-auth" placeholder="Пароль" value="${requestScope.password}">
                                <span class="reg-error">${passwordError}</span>
                            </div>
                            <div class="form-group" style="display: inline-block">
                                <input name="password_approve" type="password" class="form-control iput-auth" placeholder="Подтверждение пароля" value="${requestScope.passwordApprove}">
                            </div>
                            <div class="form-group" style="display: inline-block">
                                <input name="name" type="text" class="form-control iput-auth" placeholder="Имя" value="${requestScope.name}">
                                <span class="reg-error">${nameError}</span>
                            </div>
                            <div class="form-group" style="display: inline-block">
                                <input name="last_name" type="text" class="form-control iput-auth" placeholder="Фамилия" value="${requestScope.lastName}">
                                <span class="reg-error">${lastNameError}</span>
                            </div>
                            <div class="form-group" style="display: inline-block">
                                <select name="sex" class="form-control iput-auth">
                                    <option value="" disabled selected>Пол</option>
                                    <c:forEach var="sex" items="${sexList}">
                                        <option ${sex.id == requestScope.sex ? "selected" : ""} value="${sex.id}">${sex.sex}</option>
                                    </c:forEach>
                                </select>
                                <span class="reg-error">${sexError}</span>
                            </div>
                            <div class="form-group" style="display: inline-block">
                                <input name="birthday"  id="datepicker" class="form-control iput-auth" data-provide="datepicker" placeholder="Дата рождения" value="${requestScope.birthday}">
                                <span class="reg-error">${dateError}</span>
                            </div>
                            <div class="inline-block">
                                <input type="submit" class="btn btn-primary" value="Регистрация">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/style/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/style/datepicker/js/bootstrap-datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/style/datepicker/locales/bootstrap-datepicker.ru.min.js"></script>

    <script>
        $('#datepicker').datepicker({
            format: "yyyy-mm-dd",
            language: "ru",
            autoclose: true,
            defaultViewDate: { year: 1989, month: 01, day: 08 }
        });
    </script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/style/js/bootstrap.min.js"/>
</body>
</html>
