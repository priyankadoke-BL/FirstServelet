<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16-11-2021
  Time: 02:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login Success</title>
</head>
<body>
<h3>Hi <%=request.getAttribute("user")%>,Login successful.</h3>
<a href="login.html">Login Page</a>
</body>
</html>