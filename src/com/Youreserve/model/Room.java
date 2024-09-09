package com.Youreserve.model;

public class Room {

    private String roomNumber;
    private String roomType;
    private boolean isAvailable;


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
