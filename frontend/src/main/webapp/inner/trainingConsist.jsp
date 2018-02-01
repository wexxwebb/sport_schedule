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
            <ul class="nav nav-tabs nav-justified">
                <li role="presentation">
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
                <h3 class="h-main">План тренировки #${training.id}, дата '${training.trainingDate}'</h3>
                <h5 style="color: grey;" class="h-main">дата создания '${training.createDate}'</h5>
            </div>
            <div class="row" style="margin-top: 30px">
                <div class="col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2">
                    <table id="exercise" class="table table-hover">
                        <thead>
                            <tr>
                                <th style="display: none"></th>
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
                                    <td style="display: none;">${exercise.id}</td>
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
                        <table style="display: inline-block" class="table table-hover">
                            <thead>
                                <th>Название</th>
                                <th>Подходов</th>
                                <th>Повторений</th>
                                <th>Вес</th>
                                <th style="widht: 10%"></th>
                            </thead>
                            <tbody>
                                <tr>
                                    <td style="text-align: center;">
                                        <input type="text" name="exercise_name" onclick="this.select()"
                                               class="form-control iput-auth" id="searchExercise" placeholder="Название упражнения">
                                        <input id="exercise_id" name="exerciseDataId" type="hidden">
                                    </td>
                                    <td>
                                        <input type="text" name="approach" onclick="this.select()"
                                               id="approach" class="form-control" placeholder="Подходов">
                                    </td>
                                    <td>
                                        <input type="text" name="repetition" onclick="this.select()"
                                               id="repetition" class="form-control" placeholder="Повторений">
                                    </td>
                                    <td>
                                        <input type="text" name="weigth" onclick="this.select()"
                                               id="weight" class="form-control" placeholder="Вес нагрузки">
                                    </td>
                                    <td style="text-align: right;" >
                                        <span style="cursor: pointer; font-size: 20px;
                                                color: limegreen; position: relative; top: 6px;"
                                                    class="glyphicon glyphicon-plus" onclick="addExcercise()"></span>
                                    </td>
                                </tr>
                            </tbody>
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
                source: "${pageContext.request.contextPath}/inner/searchExerciseData", // url-адрес
                minLength: 1,
                select: function(event, ui) {
                    $('#exercise_id').val(ui.item.id);
                }
            });
        </script>

        <script>

            function del(exerciseId) {
                $.ajax({url: "${pageContext.request.contextPath}/inner/delExercise",
                        method: "POST",
                        data: {id: exerciseId},
                        success: function (result) {
                            if (result == 1) {
                                var tbody = document.getElementById("exercise");
                                for (var i = 1; i < tbody.rows.length; i++) {
                                    if (tbody.rows[i].cells[0].textContent == exerciseId) {
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

            function addExcercise() {
                $.ajax({url: "${pageContext.request.contextPath}/inner/addExercise",
                        method: "POST",
                        data: { exercise_id: $('#exercise_id').val(),
                                training_id: ${training.id},
                                approach: $("#approach").val(),
                                repetition: $("#repetition").val(),
                                weigth: $("#weight").val()
                            },
                        success: function (result) {
                            //{"id":16,"exerciseId":25,"exercise":{"id":25,"name":"Гиперэкстензия"},"trainingId":1,"approach":3,"repetition":10,"weigth":10}
                            var tbody = document.getElementById("exercise");
                            var row = tbody.insertRow(tbody.rows.lenth);
                            var exercise = result;
                            var hidden = row.insertCell(0);
                            hidden.setAttribute("style", "display: none");
                            hidden.innerHTML = String(exercise.id);
                            var name = row.insertCell(1);
                            name.innerHTML = String(exercise.exercise.name);
                            var approach = row.insertCell(2);
                            approach.innerHTML = String(exercise.approach);
                            var repetition = row.insertCell(3);
                            repetition.innerHTML = String(exercise.repetition);
                            var weigth = row.insertCell(4);
                            weigth.innerHTML = String(exercise.weigth);
                            var icon = row.insertCell(5);
                            icon.setAttribute("style", "text-align: right;");
                            icon.innerHTML = "<span style='cursor: pointer;font-size: 20px; color: red;' class='glyphicon glyphicon-remove'" +
                                "aria-hidden='true' onclick='del(" + String(exercise.id) + ")'/>";
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
