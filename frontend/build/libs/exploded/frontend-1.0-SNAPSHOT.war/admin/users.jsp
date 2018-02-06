<!--
  Created by IntelliJ IDEA.
  User: admin
  Date: 16.01.2018
  Time: 17:08
  To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Список пользователей</title>

        <!-- Bootstrap -->
        <link href="../style/css/bootstrap.min.css" rel="stylesheet">
        <link href=../style/style.css rel="stylesheet">

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
                <h2 class="h-main">Список пользователей</h2>
            </div>
            <div class="row" style="margin-top: 30px">
                <div class="col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Имя</th>
                                <th>Фамилия</th>
                                <th style="width: 10%;">Пол</th>
                                <th style="width: 20%; text-align: center">Дата рождения</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${userList}">
                            <tr>
                                <td>${user.person.id}</td>
                                <td>${user.person.lastName}</td>
                                <td>${user.person.firstName}</td>
                                <td>${user.person.name.name}</td>
                                <td style="text-align: center;">${user.person.birthday}</td>
                                <%--todo add blocking/unblocking users --%>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../style/js/bootstrap.min.js"></script>
    </body>
</html>
