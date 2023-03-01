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
    private Course course;
    private int instructorID;
    private ArrayList<Student> studentGroup;

    public Group(int groupID, String name, Course course, int instructorID, ArrayList<Student> studentGroup) {
        this.groupID = groupID;
        this.name = name;
        this.course = course;
        this.instructorID = instructorID;
        this.studentGroup = studentGroup;
    }

    

    public Group() {
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
