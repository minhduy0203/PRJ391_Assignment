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
    private Instructor instructor;
    private ArrayList<Student> studentGroup;
    private ArrayList<Lecture> lecture;

    public Group() {
    }

    public ArrayList<Lecture> getLecture() {
        return lecture;
    }

    public void setLecture(ArrayList<Lecture> lecture) {
        this.lecture = lecture;
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

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public ArrayList<Student> getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(ArrayList<Student> studentGroup) {
        this.studentGroup = studentGroup;
    }

}
