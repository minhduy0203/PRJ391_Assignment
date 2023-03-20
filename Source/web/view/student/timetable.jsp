<%-- 
    Document   : timetable
    Created on : Feb 27, 2023, 10:20:14 PM
    Author     : MinhDuy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/student/timetable.css"/>

    </head>
    <body>
        <header>
            <div class="container">
                <div class="head-content">FPT University Academic Portal</div>
            </div>

        </header>


        <div class="main">
            <div class="container">
                <div class="header-container"   >
                     <div class="header-box" style="cursor: pointer;" onclick="location.href = '../home'">Home </div>
                    <div class="header-box">${sessionScope.user.student.lastName} ${sessionScope.user.student.firstName}</div>
                    <div class="header-box">Campus Hòa lạc </div>
                    <div class="header-box" onclick="location.href='${pageContext.request.contextPath}/logout'" style=" cursor: pointer;">Logout</div>
                    <img src="" alt="">
                </div>
                <div class="description">


                    <h1> Activities for ${sessionScope.user.student.rollNumber} (${sessionScope.user.student.lastName} ${sessionScope.user.student.firstName})</h1><br>
                    Note: These activities do not include extra-curriculum activities, such as club activities ...<br>
                    Chú thích: Các hoạt động trong bảng dưới không bao gồm hoạt động ngoại khóa, ví dụ như hoạt động câu lạc
                    bộ ...<br>
                    Các phòng bắt đầu bằng AL thuộc tòa nhà Alpha. VD: AL...Các phòng bắt đầu bằng BE thuộc tòa nhà Beta.
                    VD: BE,..Các phòng bắt đầu bằng G thuộc tòa nhà Gamma. VD: G201,...Các phòng tập bằng đầu bằng R thuộc
                    khu vực sân tập Vovinam.Các phòng bắt đầu bằng DE thuộc tòa nhà Delta. VD: DE,..Little UK (LUK) thuộc
                    tầng 5 tòa nhà Delta
                </div>

                <div class="calender">
                    <div class="calender-head">
                        <div class="calender-head-describe">
                            Time Schedule
                        </div>
                    </div>
                    <div class="calender-table">
                        <table>
                            <thead>
                                <tr>
                                    <th class="input" rowspan="2">
                                        <form id="myForm" action="time" method="get">
                                            <span>Year</span>
                                            <select onchange="handleSubmit()" name ="year">
                                                <c:forEach items="${requestScope.listYear}" var="y" >
                                                    <option ${y == yearCurrent ? "selected" : ""}>${y}</option>
                                                </c:forEach>
                                            </select> <br>
                                            <span>Date</span>
                                            <select  onchange="handleSubmit()" name="date">
                                                <c:forEach items="${requestScope.list}" var="date" varStatus="loop" >
                                                    <option ${param.date == loop.index + 1  ?  "selected" : "" } ${requestScope.current == loop.index + 1 ?  "selected" : ""}  value="${loop.index + 1}">${date}</option>

                                                </c:forEach>
                                            </select>
                                        </form>
                                    </th>
                                    <th>Mon</th>
                                    <th>Tue</th>
                                    <th>Wed</th>
                                    <th>Thu</th>
                                    <th>Fri</th>
                                    <th>Sat</th>
                                    <th>Sun</th>
                                </tr>
                                <tr>

                                    <c:forEach items="${days}" var ="day" >
                                        <th>${day}</th>
                                        </c:forEach>
                                </tr>
                            </thead>
                            <tbody>


                                <c:forEach varStatus="loop" begin="1" end="6" step="1">
                                    <tr>
                                        <td>Slot${loop.index}</td>
                                        <c:forEach varStatus="i" begin="2" end="8" step="1">
                                            <td>
                                                <c:forEach items="${listLec}" var="lecture" >
                                                    <c:if test="${loop.index == lecture.timeSlot.slotID }">
                                                        <c:if test="${lecture.weekDay == i.index ? true : false}">
                                                            <span id="${lecture.lectureID}"><strong>${lecture.group.course.code} At ${lecture.room.name}</strong></span><br>
                                                            <div class="time-label">
                                                                <fmt:formatDate value="${lecture.timeSlot.timeFrom}" pattern="hh:mm"></fmt:formatDate> -
                                                                <fmt:formatDate value="${lecture.timeSlot.timeTo}" pattern="hh:mm"></fmt:formatDate>
                                                            </div><br>
                                                                <c:if test="${lecture.status == null}"><span style="color: red;">Not yet</span></c:if>
                                                                <c:if test="${lecture.status != null}">
                                                                    ${lecture.status ? "<span style = \"color: green\">attend</span>" : "<span style = \"color: red\" >absent</span>"  }
                                                                </c:if>
                                                        </c:if>

                                                    </c:if>
                                                </c:forEach>

                                            </td>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
           
            <div class="footer container">
                <div class="footer-describe">
                    More note / Chú thích thêm:<br>
                    <span style="color: green;">(attended)</span>: ${sessionScope.user.student.firstName}${sessionScope.user.student.rollNumber} had attended this activity / Nguyễn Minh
                    Duy đã tham gia hoạt động này<br>
                    <span style="color: rgb(212, 19, 19);">(absent)</span>: ${sessionScope.user.student.firstName}${sessionScope.user.student.rollNumber} had NOT attended this activity /
                    Nguyễn Minh Duy đã vắng mặt buổi này<br>
                    (-): no data was given / chưa có dữ liệu<br>

                    Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: dichvusinhvien@fe.edu.vn. Điện<br>
                    thoại: (024)7308.13.13<br>
                </div>
            </div>
        </div>
        <script>
            function handleSubmit() {
                document.getElementById("myForm").submit();
            }


        </script>
    </body>
</html>
