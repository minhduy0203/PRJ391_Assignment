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
import model.Attendance;
import model.Lecture;
import model.Student;

/**
 *
 * @author MinhDuy
 */
public class AttendanceDBContext extends DBContext<Attendance> {

    @Override
    public void insert(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attendance> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Attendance> gelAllByLecture(int lectureID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Attendance> list = new ArrayList<>();
        StudentDBContext stuDB = new StudentDBContext();
        try {
            String sql = "SELECT * FROM Attendancne WHERE LectureID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, lectureID);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                Student s = new Student();
                Lecture l = new Lecture();
                s = stuDB.get(rs.getInt("StudentID"));
                l.setLectureID(rs.getInt("LectureID"));
                Boolean b = rs.getObject("Status") != null ? rs.getBoolean("Status") : null;
                a.setStudent(s);
                a.setLecture(l);
                a.setStatus(b);
                a.setComment(rs.getString("Comment"));
                list.add(a);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateAttend(int lectureID, ArrayList<Attendance> list) {
        
        try {
            connection.setAutoCommit(false);
            // update lecture
            String sql_update_lecture = "UPDATE [dbo].[Lecture]\n"
                    + "   SET \n"
                    + "      [Status] = 1\n"
                    + " WHERE LectureID = ?";
            PreparedStatement stm_update_lecture = connection.prepareStatement(sql_update_lecture);
            stm_update_lecture.setInt(1, lectureID);
            stm_update_lecture.executeUpdate();
            // update attendance
            for (Attendance attendance : list) {
                String sql_update_att = "UPDATE [dbo].[Attendancne]\n"
                        + "   SET \n"
                        + "      \n"
                        + "      [Status] = ?\n"
                        + "      ,[Record] = ?\n"
                        + "      ,[Comment] = ?\n"
                        + " WHERE LectureID = ? AND StudentID = ?";
                PreparedStatement stm_update_att = connection.prepareStatement(sql_update_att);

                stm_update_att.setBoolean(1, attendance.getStatus());
                stm_update_att.setTime(2, attendance.getRecord());
                stm_update_att.setString(3, attendance.getComment());
                stm_update_att.setInt(4, lectureID);
                stm_update_att.setInt(5, attendance.getStudent().getStudentID());
                stm_update_att.executeUpdate();
            }
            connection.commit();

        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
