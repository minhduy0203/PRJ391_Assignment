/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author MinhDuy
 */
public class Lecture {
    private int lectureID;
    private Group group;
    private Instructor instructor;
    private TimeSlot timeSlot;
    private Room room;
    private Date date;

    public Lecture(int lectureID, Group group, Instructor instructor, TimeSlot timeSlot, Room room, Date date) {
        this.lectureID = lectureID;
        this.group = group;
        this.instructor = instructor;
        this.timeSlot = timeSlot;
        this.room = room;
        this.date = date;
    }

    public Lecture() {
    }

    public int getLectureID() {
        return lectureID;
    }

    public void setLectureID(int lectureID) {
        this.lectureID = lectureID;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
