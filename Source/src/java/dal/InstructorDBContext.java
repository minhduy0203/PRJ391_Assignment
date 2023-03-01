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

/**
 *
 * @author MinhDuy
 */
public class InstructorDBContext extends DBContext<Instructor> {

    @Override
    public void insert(Instructor model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Instructor model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Instructor model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Instructor get(int id) {
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT   [InstructorID]\n"
                    + "      ,[RollNumber]\n"
                    + "      ,[Firstname]\n"
                    + "      ,[Lastname]\n"
                    + "      ,[DOB]\n"
                    + "      ,[Gender]\n"
                    + "      ,[Address]\n"
                    + "      ,[Telephone]\n"
                    + "      ,[Email]\n"
                    + "      ,[Department]\n"
                    + "  FROM [PRJ301_Assignment1].[dbo].[Instructor] WHERE InstructorID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                Instructor i = new Instructor();
                i.setInstructorID(rs.getInt("InstructorID"));
                i.setRollNumber(rs.getString("RollNumber"));
                i.setFirstName(rs.getString("Firstname"));
                i.setLastName(rs.getString("Lastname"));
                i.setDob(rs.getDate("DOB"));
                i.setGender(rs.getBoolean("Gender"));
                i.setAddress(rs.getString("Address"));
                i.setTelephone(rs.getInt("Telephone"));
                i.setEmail(rs.getString("Email"));
                i.setDepartment(rs.getString("Department"));
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Instructor> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
