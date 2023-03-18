/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecture;
import model.Student;

/**
 *
 * @author MinhDuy
 */
public class StudentDBContext extends DBContext<Student> {

    @Override
    public void insert(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int id) {
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT  [StudentID]\n"
                    + "      ,[Rollnumber]\n"
                    + "      ,[Firstname]\n"
                    + "      ,[Lastname]\n"
                    + "      ,[DOB]\n"
                    + "      ,[Gender]\n"
                    + "      ,[Address]\n"
                    + "      ,[Telephone]\n"
                    + "      ,[Email]\n"
                    + "  FROM [PRJ301_Assignment1].[dbo].[Student]\n"
                    + "  WHERE Student.StudentID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setStudentID(rs.getInt("StudentID"));
                s.setRollNumber(rs.getString("Rollnumber"));
                s.setFirstName(rs.getString("Firstname"));
                s.setLastName(rs.getString("Lastname"));
                s.setDob(rs.getDate("DOB"));
                s.setGender(rs.getBoolean("Gender"));
                s.setAddress(rs.getString("address"));
                s.setTelephone(rs.getInt("Telephone"));
                s.setEmail(rs.getString("Email"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Student> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Student> getStudentByLecture(int lectureID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Student> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Attendancne WHERE LectureID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, lectureID);
            rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s = this.get(rs.getInt("StudentID"));
                list.add(s);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateAttend(Student s, int lectureID, boolean status, Time time, String comment) {
        PreparedStatement stm = null;

        try {

            String sql = "UPDATE [dbo].[Attendancne]\n"
                    + "   SET \n"
                    + "      \n"
                    + "      [Status] = ?\n"
                    + "      ,[Record] = ?\n"
                    + "      ,[Comment] = ?\n"
                    + " WHERE LectureID = ? AND StudentID = ?";

            stm = connection.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setTime(2, time);
            stm.setString(3, comment);
            stm.setInt(4, lectureID);
            stm.setInt(5, s.getStudentID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Student> getAllStudentByGroup(int groupID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Student> students = new ArrayList<>();
        LectureDBContext db = new LectureDBContext();
        try {
            String sql = "SELECT * FROM StudentGroup WHERE GroupID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, groupID);
            rs = stm.executeQuery();
            while (rs.next()) {
                int sid = rs.getInt("StudentID");
                Student s = this.get(sid);
                ArrayList<Lecture> list = db.getAllByStudentGroup(sid, groupID);
                s.setLecture(list);
                students.add(s);

            }
            return students;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
