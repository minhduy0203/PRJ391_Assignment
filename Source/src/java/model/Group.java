/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author MinhDuy
 */
public class Group {
    private int groupID;
    private String name;
    private int courseID;
    private int instructorID;
    ArrayList<Student> studentGroup;

    public Group(int groupID, String name, int courseID, int instructorID, ArrayList<Student> studentGroup) {
        this.groupID = groupID;
        this.name = name;
        this.courseID = courseID;
        this.instructorID = instructorID;
        this.studentGroup = studentGroup;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public ArrayList<Student> getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(ArrayList<Student> studentGroup) {
        this.studentGroup = studentGroup;
    }
    
    
}
