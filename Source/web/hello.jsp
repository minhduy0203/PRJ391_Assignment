<%-- 
    Document   : hello
    Created on : Mar 1, 2023, 10:48:54 PM
    Author     : MinhDuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello ${sessionScope.user.student.email}</h1>
    </body>
</html>
