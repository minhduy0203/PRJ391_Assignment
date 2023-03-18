<%-- 
    Document   : attendance.jsp
    Created on : Mar 6, 2023, 9:47:38 PM
    Author     : MinhDuy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/instructor/attendance.css"/>
    </head>
    <body>
        <div class="container">
            <div class="header-container">
                    <div class="header-box">Campus Hòa lạc </div>
                    <div class="header-box" onclick="location.href='${pageContext.request.contextPath}/logout'" style=" cursor: pointer;">Logout</div>
                    <img src="" alt="">
            </div>
        </div>

        <div class="main">
            <div class="container">

                <form method="post" action="attendance">
                    <input type="hidden" value="${param.id}" name="lectureID">
                     <input type="hidden" value="${param.groupID}" name="groupID">
                    <div class="table">
                        <table>
                            <thead>
                                <tr>
                                    <th>Index</th>
                                    <th>Image</th>
                                    <th>Name</th>
                                    <th>RollNumber</th>
                                    <th>Comment</th>
                                    <th>Attendance</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${atts}" var="a" varStatus="loop">
                                <input type ="hidden" value="${a.student.studentID}" name="studentID">
                                    <tr>
                                        <td>${loop.index}</td>
                                        <td>
                                            <img src="" alt="">
                                        </td>
                                        <td>${a.student.lastName} ${a.student.firstName} </td>
                                        <td>${a.student.rollNumber}</td>
                                        <td>
                                            <input type="text" name="comment" id="" value="${a.comment}">
                                        </td>
                                        <td>
                                            Attend<input ${a.status ? "checked" : ""} value="attend" type="radio" name="check${loop.index}">
                                            Absent <input ${!a.status ? "checked" : ""} value="absent" type="radio" name="check${loop.index}">
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                    </div>
                    <div class="container submit-button"> <input type="submit" value="submit"></div>

                </form>
            </div>
    </body>
</html>
