package com.Youreserve.entity.roomTypes;

import com.Youreserve.enums.LuxuryLevel;
import com.Youreserve.entity.Room;

public class SuiteRoom extends Room {

    private int id;
    private LuxuryLevel luxuryLevel;
    private boolean hasBalcony;

    public SuiteRoom(String roomNumber, String roomType, double price ,int id, LuxuryLevel luxuryLevel, boolean hasBalcony) {
        super(roomNumber, roomType , price);
        this.id = id;
        this.luxuryLevel = luxuryLevel;
        this.hasBalcony = hasBalcony;
    }

    public int getId() {
        return id;
    }

    public LuxuryLevel getLuxuryLevel() {
        return luxuryLevel;
    }

    public boolean hasBalcony() {
        return hasBalcony;
    }

    public void setBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }


    @Override
    public String toString() {
        return  "\t\t-----------------------------------------------"+"\n" +
                "\u001B[33m\t\tROOM TYPE  : " + roomType.toUpperCase() + "\t\tPrice : " + price + "MAD" +  "\n\u001B[0m"+
                "\t\t-----------------------------------------------"+"\n" +
                "\t\tRoom Number     : " + roomNumber + "\n" +
                "\t\tLuxury Level    : " + luxuryLevel + "\n" +
                "\t\tHas Balcony     : " + (hasBalcony ? "Yes" : "No") + "\n" +
                "\t\tAvailability    : " + (isAvailable ? "Available" : "Occupied") + "\n" +
                "\t\t-----------------------------------------------";
    }

}
