/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import controller.authentication.BaseRequiredAuthenticationTeacher;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import model.Student;
import model.User;

/**
 *
 * @author MinhDuy
 */
public class AttendanceController extends BaseRequiredAuthenticationTeacher {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        StudentDBContext db = new StudentDBContext();
        String lectureID_raw = req.getParameter("id");
        ArrayList<Student> list = db.getStudentByLecture(Integer.parseInt(lectureID_raw));
        req.setAttribute("students", list);
        req.getRequestDispatcher("../view/instructor/attendance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        StudentDBContext db = new StudentDBContext();
        String lectureID_raw = req.getParameter("lectureID");
        ArrayList<Student> list = db.getStudentByLecture(Integer.parseInt(lectureID_raw));
        String[] comments = req.getParameterValues("comment");
        Boolean[] booleans = new Boolean[comments.length];
        for (int i = 0; i < comments.length ; i++) {
            String check = req.getParameter("check" + i);
            booleans[i] = check.equals("attend");
        }
        
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            db.updateAttend(s, Integer.parseInt(lectureID_raw), booleans[i], getCurrentTime(), comments[i]);
        }

    }
    
    
    public Time getCurrentTime(){
        return java.sql.Time.valueOf(LocalTime.now());
    }

}
