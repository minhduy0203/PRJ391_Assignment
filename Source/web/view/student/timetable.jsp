<%-- 
    Document   : timetable
    Created on : Feb 27, 2023, 10:20:14 PM
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
                <div class="header-container">
                    
                    <div class="header-box">Nguyễn Minh Duy</div>
                    <div class="header-box">Campus Hòa lạc </div>
                    <div class="header-box">Logout</div>
                    <img src="" alt="">
                </div>
                <div class="description">


                    <h1> Activities for DuyNMHE172040 (Nguyễn Minh Duy)</h1><br>
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
                                <tr>
                                    <td>Slot1</td>
                                    <td>
                                        <span><strong>PRJ301 At BE-304</strong></span><br>
                                        <div class="time-label">7:30 - 9:50</div>
                                        <span style="color: green;">(attended)</span>
                                    </td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr >
                                    <td>Slot2</td>
                                    <td>  <span><strong>PRJ301 At BE-304</strong></span><br>
                                        <div class="time-label">7:30 - 9:50</div><br>
                                        <span style="color: red;">(absent)</span></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Slot3</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Slot4</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Slot5</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Slot6</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="footer container">
                <div class="footer-describe">
                    More note / Chú thích thêm:<br>
                    <span style="color: green;">(attended)</span>: DuyNMHE172040 had attended this activity / Nguyễn Minh
                    Duy đã tham gia hoạt động này<br>
                    <span style="color: rgb(212, 19, 19);">(absent)</span>: DuyNMHE172040 had NOT attended this activity /
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
