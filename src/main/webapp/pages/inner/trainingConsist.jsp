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
        <title>План тренировки</title>

        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
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
            <div class="row">
                <h3 class="h-main">План тренировки #${training.id}, дата '${training.trainingDate}'</h3>
                <h5 style="color: grey;" class="h-main">дата создания '${training.createDate}'</h5>
            </div>
            <div class="row" style="margin-top: 30px">
                <div class="col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2">
                    <table id="training" class="table table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Название</th>
                                <th>Подходов</th>
                                <th>Повторений</th>
                                <th>Вес</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="exercise" items="${exerciseList}">
                                <tr>
                                    <td>${exercise.id}</td>
                                    <td>${exercise.exerciseData.name}</td>
                                    <td>${exercise.approach}</td>
                                    <td>${exercise.repetition}</td>
                                    <td>${exercise.weigth}</td>
                                    <td style="text-align: right;">
                                        <span style="cursor: pointer; font-size: 20px; color: red;" class="glyphicon glyphicon-remove"
                                              aria-hidden="true" onclick="del(${exercise.id})"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div style="height: 30px;"></div>
                    <h4 style="text-align: center">Добавить упражнение</h4>
                    <div style="height: 25px;"></div>
                    <div style="text-align: center">
                        <table style="width: 300px; display: inline-block" class="table table-hover">
                            <tr>
                                <td style="text-align: center;">
                                    <input type="text" name="exercise_name" onclick="this.select()"
                                           class="form-control iput-auth" id="searchExercise" placeholder="Название упражнения">
                                    <input id="exerciseDataId" name="exerciseDataId" id type="hidden">
                                </td>
                                <td style="text-align: right; width: 20%" >
                                    <span style="cursor: pointer; font-size: 20px; color: limegreen; position: relative; top: 6px;" class="glyphicon glyphicon-plus" onclick="addTraining()"/>
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
        <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

        <script>
            $('#searchExercise').autocomplete({
                source: "${pageContext.request.contextPath}/searchExerciseData", // url-адрес
                minLength: 1,
                select: function(event, ui) {
                    $('#exerciseDataId').val(ui.item.id);
                }
            });
        </script>

        <script>

            function del(trainingId) {
                $.ajax({url: "${pageContext.request.contextPath}/delTraining",
                        method: "POST",
                        data: {id: trainingId},
                        success: function (result) {
                            if (result == 1) {
                                var tbody = document.getElementById("training");
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
                $.ajax({url: "${pageContext.request.contextPath}/addTraining",
                        method: "POST",
                        data: {user_id: 1, date: document.getElementById("datepicker").value},
                        success: function (result) {
                            //alert(result);
                            var tbody = document.getElementById("training");
                            var row = tbody.insertRow(tbody.rows.lenth);
                            var training = JSON.parse(result);
                            var id = row.insertCell(0);
                            id.innerHTML = String(training.id);
                            var date = row.insertCell(1);
                            date.innerHTML = String(training.trainingDate);
                            var create_date = row.insertCell(2);
                            create_date.innerHTML = String(training.createDate);
                            var icon = row.insertCell(3);
                            icon.setAttribute("style", "text-align: right;");
                            icon.innerHTML = "<span style=' cursor: pointer; font-size: 20px; color: red;' class='glyphicon glyphicon-remove'" +
                                "aria-hidden='true' onclick='del(" + String(training.id) + ")'/>";
                        }
                });
            }

            $('#datepicker').datepicker({
                format: "yyyy-mm-dd",
                language: "ru",
                autoclose: true
            });
        </script>
    </body>
</html>
