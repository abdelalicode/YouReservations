package com.Youreserve.entity;

public class Room {

    private int id;
    protected String roomNumber;
    protected String roomType;
    protected double price;
    protected boolean isAvailable;


    public Room(String roomNumber, String roomType , double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = true;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
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
