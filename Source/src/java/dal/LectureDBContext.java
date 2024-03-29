/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Group;
import model.Instructor;
import model.Lecture;
import model.Room;
import model.Student;
import model.TimeSlot;

/**
 *
 * @author MinhDuy
 */
public class LectureDBContext extends DBContext<Lecture> {

    @Override
    public void insert(Lecture model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Lecture model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Lecture model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Lecture get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Lecture> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
//get for student

    public ArrayList<Lecture> getByDate(String dateFrom, String dateTo, int studentID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Lecture> list = new ArrayList<>();
        try {

            String sql = " select Lecture.LectureID,Attendancne.StudentID,Attendancne.Status,Course.Name AS 'CourseName'  , Course.Code ,[Group].Name AS 'GroupName' ,  Room.Name as 'Roomname', Room.RoomID   , TimeSlotID,TimeFrom , TimeTo  , Date  , [Group].CourseID ,[Group].GroupID, DATEPART(WEEKDAY, Date) AS 'WeekDay'  from ((Lecture  \n"
                    + "INNER JOIN Attendancne ON Attendancne.LectureID = Lecture.LectureID\n"
                    + "INNER JOIN TimeSlot ON Lecture.TimeSlotID = TimeSlot.SlotID)\n"
                    + "INNER JOIN [Group] ON Lecture.GroupID = [Group].GroupID \n"
                    + "INNER JOIN Room ON Lecture.RoomID = Room.RoomID\n"
                    + "INNER JOIN Course ON [Group].CourseID = Course.CourseID\n"
                    + "\n"
                    + ") \n"
                    + " WHERE date between ? AND ? AND Attendancne.StudentID = ?\n"
                    + " ORDER BY SlotID ,Date";
            stm = connection.prepareStatement(sql);
            stm.setDate(1, Date.valueOf(dateFrom));
            stm.setDate(2, Date.valueOf(dateTo));
            stm.setInt(3, studentID);
            rs = stm.executeQuery();
            while (rs.next()) {
                Lecture l = new Lecture();
                Instructor intrsuctor = new Instructor();
                Group gr = new Group();
                Course course = new Course();
                TimeSlot slot = new TimeSlot();
                Room room = new Room();
//                Student student = new Student();
//                ArrayList<Student> listStu = new ArrayList<>();
                l.setLectureID(rs.getInt("LectureID"));
                // set course
                course.setCourseID(rs.getInt("CourseID"));
                course.setName(rs.getString("CourseName"));
                course.setCode(rs.getString("Code"));
                // set group
                gr.setGroupID(rs.getInt("GroupID"));
                gr.setName(rs.getString("GroupName"));
                gr.setCourse(course);
                // set slot
                slot.setSlotID(rs.getInt("TimeSlotID"));
                slot.setTimeFrom(rs.getTime("TimeFrom"));
                slot.setTimeTo(rs.getTime("TimeTo"));
                // set room
                room.setName(rs.getString("Roomname"));
                room.setRoomID(rs.getInt("RoomID"));
                //
                l.setLectureID(rs.getInt("LectureID"));
                Boolean b = rs.getObject("Status") != null ? rs.getBoolean("Status") : null;
                l.setStatus(b);
                l.setGroup(gr);
                l.setRoom(room);
                l.setTimeSlot(slot);
                l.setDate(rs.getDate("Date"));
                l.setWeekDay(rs.getInt("Weekday"));
                list.add(l);

            }
        } catch (SQLException ex) {
            Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //get for instructor
    public ArrayList<Lecture> getByDateInstructor(String dateFrom, String dateTo, int instructorID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Lecture> list = new ArrayList<>();
        try {

            String sql = "SELECT LectureID ,Course.CourseID ,Course.Name AS 'CourseName' , Code , [Group].GroupID , [Group].Name AS 'Groupname' , TimeSlotID , TimeFrom , TimeTo , Room.Name AS 'RoomName', Room.RoomID  , Status ,Date , DATEPART(WEEKDAY, Date) AS 'WeekDay'\n"
                    + "FROM Lecture INNER JOIN [Group] ON Lecture.GroupID = [Group].GroupID INNER JOIN Course ON Course.CourseID = [Group].CourseID\n"
                    + "INNER JOIN Room ON Lecture.RoomID = Room.RoomID INNER JOIN TimeSlot ON Lecture.TimeSlotID = TimeSlot.SlotID\n"
                    + "WHERE  Date BETWEEN ? AND ? AND [Group].InstructorID = ?  \n"
                    + "ORDER BY Date";
            stm = connection.prepareStatement(sql);
            stm.setDate(1, Date.valueOf(dateFrom));
            stm.setDate(2, Date.valueOf(dateTo));
            stm.setInt(3, instructorID);
            rs = stm.executeQuery();
            while (rs.next()) {
                Lecture l = new Lecture();
                Instructor intrsuctor = new Instructor();
                Group gr = new Group();
                Course course = new Course();
                TimeSlot slot = new TimeSlot();
                Room room = new Room();
//                Student student = new Student();
//                ArrayList<Student> listStu = new ArrayList<>();
                // set course
                course.setCourseID(rs.getInt("CourseID"));
                course.setName(rs.getString("CourseName"));
                course.setCode(rs.getString("Code"));
                // set group
                gr.setGroupID(rs.getInt("GroupID"));
                gr.setName(rs.getString("GroupName"));
                gr.setCourse(course);
                // set slot
                slot.setSlotID(rs.getInt("TimeSlotID"));
                slot.setTimeFrom(rs.getTime("TimeFrom"));
                slot.setTimeTo(rs.getTime("TimeTo"));
                // set room
                room.setName(rs.getString("Roomname"));
                room.setRoomID(rs.getInt("RoomID"));
                //
                l.setLectureID(rs.getInt("LectureID"));
                Boolean b = rs.getObject("Status") != null ? rs.getBoolean("Status") : null;
                l.setStatus(b);
                l.setGroup(gr);
                l.setRoom(room);
                l.setTimeSlot(slot);
                l.setDate(rs.getDate("Date"));
                l.setWeekDay(rs.getInt("Weekday"));
                list.add(l);

            }
        } catch (SQLException ex) {
            Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Lecture> getAllByStudentGroup(int sid, int gid) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Lecture> list = new ArrayList<>();
        try {

            String sql = "SELECT a.StudentID , a.Status , l.LectureID , g.GroupID , g.Name + ' ' + c.Name AS 'GroupName' FROM StudentGroup sg INNER JOIN Student s ON s.StudentID =sg.StudentID \n"
                    + "INNER JOIN [Group] g ON sg.GroupID = g.GroupID INNER JOIN Lecture l ON l.GroupID = g.GroupID\n"
                    + "INNER JOIN Course c on c.CourseID = g.CourseID\n"
                    + "INNER JOIN Attendancne a ON l.LectureID = a.LectureID AND s.StudentID = a.StudentID \n"
                    + "WHERE g.GroupID = ? AND s.StudentID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            stm.setInt(2, sid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Lecture l = new Lecture();
                Boolean b = rs.getObject("Status") != null ? rs.getBoolean("Status") : null;

                Group g = new Group();
                g.setGroupID(rs.getInt("GroupID"));
                g.setName(rs.getString("GroupName"));
                
                l.setStatus(b);
                l.setLectureID(rs.getInt("LectureID"));
                l.setGroup(g);
                list.add(l);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
