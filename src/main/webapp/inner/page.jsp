<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.01.2018
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title page</title>

</head>
<body>
    <table>
        <c:forEach var="user" items="${requestScope.users}">
            <c:out value="${user.value}"></c:out>
            <c:out value="${user.login}"></c:out>
        </c:forEach>
    </table>
</body>
</html>
