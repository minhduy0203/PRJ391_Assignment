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
import java.util.ArrayList;
import model.Student;
import model.User;

/**
 *
 * @author MinhDuy
 */
public class GroupAttendCheckController extends BaseRequiredAuthenticationTeacher{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        int groupID = Integer.parseInt(req.getParameter("groupID"));
        StudentDBContext db = new StudentDBContext();
        ArrayList<Student> students = db.getAllStudentByGroup(groupID);
        req.setAttribute("list", students);
        req.getRequestDispatcher("../view/instructor/statistic/statistic.jsp").forward(req, resp);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        
    }
    
}
