<%-- 
    Document   : home.jsp
    Created on : Mar 9, 2023, 10:09:33 PM
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/home/home.css"/>
    </head>
    <body>
        <section class="vh-100" style="">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col col-xl-10">
                        <div class="card" style="border-radius: 1rem;">
                            <div class="row g-0">
                                <div class="col-md-6 col-lg-5 d-none d-md-block">
                                    <img src="https://truyenhinhnghean.vn/file/4028eaa46735a26101673a4df345003c/4028eaa467f477c80167f4aa053f0c68/012021/DHFPT43591612093890_20210131185643.jpg"
                                         alt="login form" class="img-fluid"
                                         style="object-fit: cover;height: 40rem;border-radius: 1rem 0 0 1rem;" />
                                </div>
                                <div class="col-md-6 col-lg-7  ">
                                    <div class="right-container">

                                        <div class="header-container">
                                            <c:if test="${sessionScope.user.student != null}" >
                                                <div class="header-box">${sessionScope.user.student.lastName} ${sessionScope.user.student.firstName} </div>
                                                <div class="header-box">Campus ${sessionScope.user.campus} </div>
                                                <div class="header-box" style="cursor: pointer;" onclick="location.href = 'logout'"  >Logout</div>
                                            </c:if>

                                            <c:if test="${sessionScope.user.instructor != null}" >
                                                <div class="header-box">${sessionScope.user.instructor.lastName} ${sessionScope.instructor.student.firstName} </div>
                                                <div class="header-box">Campus ${sessionScope.user.campus} </div>
                                                <div class="header-box" style="cursor: pointer;" onclick="location.href = 'logout'"  >Logout</div>
                                            </c:if>

                                            <c:if test="${sessionScope.user == null }" >
                                                <div class="header-box" style="cursor: pointer;" onclick="location.href = 'login'">Login</div>
                                            </c:if>
                                            <img src="" alt="">
                                        </div>

                                        <div class="content">
                                            Welcome to FPT <br> University Portal
                                        </div>
                                        <c:if test="${sessionScope.user.student != null}">
                                        <div class="timetable">

                                            <a href="student/time"> Weeklytime table</a>
                                        </div>
                                        </c:if>
                                        <c:if test="${sessionScope.user.instructor != null}" >
                                            <div class="attendance">

                                                <a href=""> Attendance</a>
                                            </div>
                                        </c:if>



                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </body>

</html>
