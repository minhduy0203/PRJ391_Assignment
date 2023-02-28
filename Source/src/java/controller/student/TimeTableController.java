/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author MinhDuy
 */
public class TimeTableController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        handleDate(req, resp);
        req.getRequestDispatcher("../view/student/timetable.jsp").forward(req, resp);
    }

    public static int getTotalWeeksInYear(int year) {
        int totalWeeks = 0;
        Calendar calendar = Calendar.getInstance();
        for (int month = 0; month < 12; month++) {
            int day = 1;
            do {
                calendar.set(year, month, day);
                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                    totalWeeks++;
                }
                day++;
            } while (day <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        return totalWeeks;
    }

    public static ArrayList<String> getAllDayWeek(int year) {
        ArrayList<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        int totalWeek = getTotalWeeksInYear(2023);
        for (int i = 0; i < totalWeek; i++) {
            Calendar cal = Calendar.getInstance(Locale.GERMANY);
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.WEEK_OF_YEAR, i + 1);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            String weekday = "";
//        resp.getWriter().print(sdf.format(cal.getTime()));
            weekday = weekday + sdf.format(cal.getTime()) + "-";
            cal.add(Calendar.DATE, 6);
            weekday += sdf.format(cal.getTime());
            list.add(weekday);
        }
        return list;

    }

    public static ArrayList<String> getEachDayByWeek(int weekNumber) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, weekNumber);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        ArrayList<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        for (int i = 0; i < 7; i++) {
            String date;
            date = sdf.format(cal.getTime());
            list.add(date);
            cal.add(Calendar.DATE, 1);
        }
        return list;
    }

    public static int getCurrentWeek() {
        Calendar cal = Calendar.getInstance();
        int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);
        return currentWeek;
    }

    public static void handleDate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dateInput = req.getParameter("date");
        String year_raw = req.getParameter("year");
        int year = 2023;
        int[] listYear = {2021, 2022, 2023, 2024};
        req.setAttribute("listYear", listYear);
        ArrayList<String> list = new ArrayList<>();
        if (year_raw == null) {
            // get current year
            list = getAllDayWeek(year);
        } else {
            year = Integer.parseInt(year_raw);
            list = getAllDayWeek(year);
        }
        req.setAttribute("yearCurrent", year);
        req.setAttribute("list", list);
        if (req.getParameter("date") == null || dateInput == "2023") {
            int currentWeek = getCurrentWeek();
            ArrayList<String> allDay = getEachDayByWeek(currentWeek);
            req.setAttribute("current", currentWeek);
            req.setAttribute("days", allDay);
        } else {

            ArrayList<String> allDay = getEachDayByWeek(Integer.parseInt(dateInput));
            req.setAttribute("days", allDay);
        }

    }

}
