<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.01.2018
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
    <%--<%=request.getAttribute("message")%>--%>
    <%
        long currentTime = System.currentTimeMillis();
        String timeMessage = "Current time = " + currentTime;
    %>
    <%= timeMessage%>

    <form method="post" action="${pageContext.request.contextPath}/login">
        <input name="login" type="text" value="user">
        <input name="password" type="password" value="password">
        <input type="submit" value="login">
        <p style="color: red;"><%= request.getParameter("error") != null ? request.getParameter("error") : ""%></p>
    </form>

</body>
</html>
