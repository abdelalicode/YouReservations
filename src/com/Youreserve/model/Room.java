package com.Youreserve.model;

public class Room {

    private int id;
    protected String roomNumber;
    protected String roomType;
    protected boolean isAvailable;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room(String roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = true;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public String toString() {
        return "Room number: " +roomNumber + " Type: " + roomType + " Available: " + isAvailable;
    }
}
