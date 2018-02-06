<%@ page import="common.SDF" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!--
  Created by IntelliJ IDEA.
  User: admin
  Date: 16.01.2018
  Time: 17:08
  To change this template use File | Settings | File Templates.
-->
<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %> -->
<!--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> -->
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Список тренировок</title>

        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/style/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/style/datepicker/css/bootstrap-datepicker.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="container">
            <ul class="nav nav-tabs nav-justified">
                <li role="presentation" class="active">
                    <a href="${pageContext.request.contextPath}/inner/dashboard"><span class="glyphicon glyphicon-home"></span></a>
                </li>
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/inner/exerciseData">Список упражнений</a>
                </li>
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/logout">Выйти</a>
                </li>
            </ul>
        </div>
        <div class="container">
            <div class="row">
                <h3 class="h-main">Список тренировок</h3>
                <h5 class="h-main">Привет, ${firstName}</h5>
            </div>
            <div class="row" style="margin-top: 30px">
                <div class="col-md-4 col-lg-4">
                    <h4 class="h-main">Прошедшие тренировки</h4>
                    <table id="pastTraining" class="table table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Дата</th>
                                <th style="width: 10%"></th>
                                <th style="width: 10%;"></th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="training" items="${pastTrainingList}">
                                <tr>
                                    <td>${training.id}</td>
                                    <td>${training.trainingDate}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/inner/trainingConsist?trainingId=${training.id}">
                                            <span style="color: yellow; font-size: 20px;" class="glyphicon glyphicon-edit"></span>
                                        </a>
                                    </td>
                                    <td style="text-align: right;">
                                        <span style="cursor: pointer; font-size: 20px; color: red;" class="glyphicon glyphicon-remove"
                                              aria-hidden="true" onclick="del(${training.id}, 0)"></span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-4 col-lg-4">
                    <h4 class="h-main">Сегодня</h4>
                    <table id="todayTraining" class="table table-hover">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Дата</th>
                            <th style="width: 10%"></th>
                            <th style="width: 10%;"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="training" items="${todayTrainingList}">
                            <tr>
                                <td>${training.id}</td>
                                <td>${training.trainingDate}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/inner/trainingConsist?trainingId=${training.id}">
                                        <span style="color: yellow; font-size: 20px;" class="glyphicon glyphicon-edit"></span>
                                    </a>
                                </td>
                                <td style="text-align: right;">
                                        <span style="cursor: pointer; font-size: 20px; color: red;" class="glyphicon glyphicon-remove"
                                              aria-hidden="true" onclick="del(${training.id}, 1)"></span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-4 col-lg-4">
                    <h4 class="h-main">Запланированные тренировки</h4>
                    <table id="futureTraining" class="table table-hover">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Дата</th>
                            <th style="width: 10%"></th>
                            <th style="width: 10%;"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="training" items="${futureTrainingList}">
                            <tr>
                                <td>${training.id}</td>
                                <td>${training.trainingDate}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/inner/trainingConsist?trainingId=${training.id}">
                                        <span style="color: yellow; font-size: 20px;" class="glyphicon glyphicon-edit"></span>
                                    </a>
                                </td>
                                <td style="text-align: right;">
                                        <span style="cursor: pointer; font-size: 20px; color: red;" class="glyphicon glyphicon-remove"
                                              aria-hidden="true" onclick="del(${training.id}, 2)"></span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
                <div>
                    <div style="height: 30px;"></div>
                    <h4 style="text-align: center">Добавить тренировку</h4>
                    <div style="height: 25px;"></div>
                    <div style="text-align: center">
                        <table style="width: 300px; display: inline-block" class="table table-hover">
                            <tr>
                                <td style="text-align: center;">
                                    <form>
                                        <input name="date_training" id="datepicker" class="form-control iput-auth"
                                               data-provide="datepicker" style="text-align: center; display: inline-block"
                                               placeholder="Дата тренировки" >
                                        <input id="user_id" name="user_id" value="1" type="hidden">
                                    </form>
                                </td>
                                <td style="text-align: right; width: 20%" >
                                    <span style="cursor: pointer; font-size: 20px; color: limegreen; position: relative; top: 6px;" class="glyphicon glyphicon-plus" onclick="addTraining()"></span>
                                </td>
                            </tr>
                        </table>
                    </div>

                </div>
            </div>
        </div>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="${pageContext.request.contextPath}/style/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/style/datepicker/js/bootstrap-datepicker.js"></script>
        <script src="${pageContext.request.contextPath}/style/datepicker/locales/bootstrap-datepicker.ru.min.js"></script>

<%--suppress EqualityComparisonWithCoercionJS --%>
        <script>

            function del(trainingId, timePeriod) {
                $.ajax({url: "${pageContext.request.contextPath}/inner/delTraining",
                        method: "POST",
                        data: {id: trainingId},
                        success: function (result) {
                            if (result == 1) {
                                var tbody;
                                switch (timePeriod) {
                                    case 0: tbody = document.getElementById('pastTraining'); break;
                                    case 1: tbody = document.getElementById('todayTraining'); break;
                                    case 2: tbody = document.getElementById('futureTraining'); break;
                                }
                                for (var i = 1; i < tbody.rows.length; i++) {
                                    if (tbody.rows[i].cells[0].textContent == trainingId) {
                                        tbody.deleteRow(i);
                                        break;
                                    }
                                }
                            } else {
                                alert("На этот объект ссылаются другие объекты. Невозможно удалить!")
                            }
                        }
                });

            }

            function addTraining() {
                $.ajax({url: "${pageContext.request.contextPath}/inner/addTraining",
                        method: "POST",
                        data: {user_id: ${userId}, date: document.getElementById("datepicker").value},
                        success: function (result) {
                            var currentDay = new Date();
                            currentDay.setHours(0, 0, 0, 0);
                            var newDate = new Date(document.getElementById("datepicker").value);
                            newDate.setHours(0, 0, 0, 0);
                            var tbody;
                            var cur_table;
                            if (newDate.getTime() < currentDay.getTime()) {
                                tbody = document.getElementById("pastTraining");
                                cur_table = 0;
                            }
                            if (newDate.getTime() == currentDay.getTime()) {
                                tbody = document.getElementById("todayTraining");
                                cur_table = 1;
                            }
                            if (newDate.getTime() > currentDay.getTime()) {
                                tbody = document.getElementById("futureTraining");
                                cur_table = 2;
                            }
                            //alert(result);

                            var row = tbody.insertRow(tbody.rows.lenth);
                            var training = result;
                            var id = row.insertCell(0);
                            id.innerHTML = String(training.id);
                            var date = row.insertCell(1);
                            date.innerHTML = String(training.trainingDate);
                            var open_icon = row.insertCell(2);
                            open_icon.innerHTML = "<a href='${pageContext.request.contextPath}/inner/trainingConsist?trainingId=" + String(training.id) + "'>" +
                                " <span style='color: yellow; font-size: 20px;' class='glyphicon glyphicon-edit'/> " +
                                "</a>";
                            var del_icon = row.insertCell(3);
                            del_icon.setAttribute("style", "text-align: right;");
                            del_icon.innerHTML = "<span style=' cursor: pointer; font-size: 20px; color: red;' class='glyphicon glyphicon-remove'" +
                                "aria-hidden='true' onclick='del(" + String(training.id) + ", " + String(cur_table) + ")'></span>";
                        }
                });
            }

            $('#datepicker').datepicker({
                format: "yyyy-mm-dd",
                language: "ru",
                autoclose: true,
                todayBtn: "linked"
            });
        </script>
    </body>
</html>
