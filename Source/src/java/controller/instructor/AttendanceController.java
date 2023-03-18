/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import controller.authentication.BaseRequiredAuthenticationTeacher;
import dal.AttendanceDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import model.Attendance;
import model.Student;
import model.User;

/**
 *
 * @author MinhDuy
 */
public class AttendanceController extends BaseRequiredAuthenticationTeacher {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        AttendanceDBContext db = new AttendanceDBContext();
        String lectureID_raw = req.getParameter("id");
        ArrayList<Attendance> list = db.gelAllByLecture(Integer.parseInt(lectureID_raw));
        req.setAttribute("atts", list);
        req.getRequestDispatcher("../view/instructor/attendance.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        ArrayList<Attendance> atts = new ArrayList<>();
        AttendanceDBContext db = new AttendanceDBContext();
        String lectureID_raw = req.getParameter("lectureID");
        String[] students = req.getParameterValues("studentID");
        String[] comments = req.getParameterValues("comment");
        Boolean[] booleans = new Boolean[comments.length];
        for (int i = 0; i < students.length; i++) {
            Student s = new Student();
            s.setStudentID(Integer.parseInt(students[i]));
            Attendance a = new Attendance();
            a.setStudent(s);
            String check = req.getParameter("check" + i);
            booleans[i] = check.equals("attend");
            a.setStatus(booleans[i]);
            a.setComment(comments[i]);
            a.setRecord(getCurrentTime());
            atts.add(a);
            
        }
        int absentCount = 0;
        for (int i = 0; i < booleans.length; i++) {
            if (!booleans[i]) {
                absentCount++;
            }
        }
        int attendCount = booleans.length - absentCount;
        db.updateAttend(Integer.parseInt(lectureID_raw), atts);
        resp.sendRedirect("statistic?groupID=" + req.getParameter("groupID") + "&absent="+absentCount + "&attend="+attendCount);
        
    }
    
    public Time getCurrentTime() {
        return java.sql.Time.valueOf(LocalTime.now());
    }
    
}
