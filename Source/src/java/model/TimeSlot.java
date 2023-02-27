/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Time;

/**
 *
 * @author MinhDuy
 */
public class TimeSlot {

    private int slotID;
    private Time timeFrom;
    private Time timeTo;

    public TimeSlot() {
    }

    public TimeSlot(int slotID, Time timeFrom, Time timeTo) {
        this.slotID = slotID;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public Time getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Time timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Time getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Time timeTo) {
        this.timeTo = timeTo;
    }

}
