<%-- 
    Document   : statistic
    Created on : Mar 14, 2023, 10:57:35 AM
    Author     : MinhDuy
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        .head-content {
            font-style: normal;
            font-weight: 861;
            font-size: 30px;
            line-height: 150%;
            /* or 144px */

            letter-spacing: -0.022em;

            color: #000000;
        }

        .header-box {
            background-color: #FE655C;
            color: white;
            margin: 5px;
            display: inline-block;
            padding: 5px;
            border-radius: 5px;
        }

        .header-container {
            display: flex;
            justify-content: flex-end;
            margin-right: 10px;

        }

        .content {
            display: flex;
            justify-content: center;
            align-items: center;
            width:100%;
            height:100%;
        }
        .loader-wrapper {
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0;
            left: 0;
            background-color: #242f3f;
            display:flex;
            justify-content: center;
            align-items: center;
        }
        .loader {
            display: inline-block;
            width: 30px;
            height: 30px;
            position: relative;
            border: 4px solid #Fff;
            animation: loader 2s infinite ease;
        }
        .loader-inner {
            vertical-align: top;
            display: inline-block;
            width: 100%;
            background-color: #fff;
            animation: loader-inner 2s infinite ease-in;
        }

        @keyframes loader {
            0% {
                transform: rotate(0deg);
            }
            25% {
                transform: rotate(180deg);
            }
            50% {
                transform: rotate(180deg);
            }
            75% {
                transform: rotate(360deg);
            }
            100% {
                transform: rotate(360deg);
            }
        }

        @keyframes loader-inner {
            0% {
                height: 0%;
            }
            25% {
                height: 0%;
            }
            50% {
                height: 100%;
            }
            75% {
                height: 100%;
            }
            100% {
                height: 0%;
            }
        }
    </style>
    <body>

        <div class="container">
            <div class="header-container"   >
                <div class="header-box" style="cursor: pointer;" onclick="location.href = '../home'">Home </div>
                <div class="header-box">${sessionScope.user.instructor.lastName} ${sessionScope.instructor.student.firstName}</div>
                <div class="header-box">Campus Hòa lạc </div>
                <div class="header-box" onclick="location.href = '${pageContext.request.contextPath}/logout'" style=" cursor: pointer;">Logout</div>
                <img src="" alt="">
            </div>
            <canvas  id="myChart" style=" width:100%;max-width:600px; margin: 0 auto"></canvas>
            <h1>List attendance of student in group ${requestScope.list[0].lecture[0].group.name}</h1>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Absent Percentage</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.list}" var="s">
                        <c:set var="percent" value="${s.precentAbsent()}"></c:set>
                        <tr class="${percent >= 10 && percent < 15  ? "table-warning" :""} ${s.precentAbsent() >= 20 ? "table-danger" :""}" }>
                            <td>${s.rollNumber}</td>
                            <td>${s.lastName} ${s.firstName} </td>
                            <td> <fmt:formatNumber type = "number" 
                                              minFractionDigits = "1" value = "${percent}" />%</td>


                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="loader-wrapper">
                <span class="loader"><span class="loader-inner"></span></span>
            </div>
        </div>
        <script>
            var attend = ${param.attend};
            var absent = ${param.absent};
            var xValues = ["Attend", "Absent"];
            var yValues = [attend, absent];
            var barColors = [

                "#00aba9",
                "#b91d47"

            ];

            new Chart("myChart", {
                type: "pie",
                data: {
                    labels: xValues,
                    datasets: [{
                            backgroundColor: barColors,
                            data: yValues
                        }]
                },
                options: {
                    title: {
                        display: true,
                        text: "Percentage of student attendance today "
                    }
                }
            });

            $(window).on("load", function () {

                $(".loader-wrapper").fadeOut("slow");
            });
        </script>
    </body>
</html>
