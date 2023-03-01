/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Instructor;
import model.Student;
import model.User;

/**
 *
 * @author MinhDuy
 */
public class UserDBContext extends DBContext<User> {

    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<User> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public User getByUser(String username, String password) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            String sql = "SELECT  [UserID]\n"
                    + "      ,[Username]\n"
                    + "      ,[Password]\n"
                    + "      ,[Campus]\n"
                    + "      ,[StudentID]\n"
                    + "      ,[InstructorID]\n"
                    + "  FROM [PRJ301_Assignment1].[dbo].[User]\n"
                    + "  WHERE Username = ? AND Password = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                StudentDBContext dbStu = new StudentDBContext();
                InstructorDBContext dbIns = new InstructorDBContext();
                User u = new User();
                u.setUsername(rs.getString("Username"));
                u.setPassword(rs.getString("Password"));
                u.setCampus(rs.getString("Campus"));
                int studentID = rs.getInt("StudentID");
                Student s = dbStu.get(studentID);
                int instructorID = rs.getInt("InstructorID");
                u.setStudent(s);
                Instructor i = dbIns.get(instructorID);
                u.setInstructor(i);
                return u;

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
