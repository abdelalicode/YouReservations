package com.Youreserve.model.roomTypes;

import com.Youreserve.enums.BedType;
import com.Youreserve.model.Room;

public class DoubleRoom extends Room {

    private int id;
    private int numberBeds;
    private BedType bedtypes;


    public DoubleRoom(String roomNumber, String roomType, int id , int numberBeds, BedType bedtypes) {
        super(roomNumber, roomType);
        this.id = id;
        this.numberBeds = numberBeds;
        this.bedtypes = bedtypes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberBeds() {
        return numberBeds;
    }

    public void setNumberBeds(int numberBeds) {
        this.numberBeds = numberBeds;
    }

    public BedType getBedtypes() {
        return bedtypes;
    }

    public void setBedtypes(BedType bedtypes) {
        this.bedtypes = bedtypes;
    }

    @Override
    public String toString() {
        return  "\t\t--------------------------------"+"\n" +
                "\t\t**ROOM TYPE**   : " + roomType.toUpperCase() + "\n" +
                "\t\t--------------------------------"+"\n" +
                "\t\tRoom Number     : " + roomNumber + "\n" +
                "\t\tNumber of Beds  : " + numberBeds + "\n" +
                "\t\tBed Types       : " + bedtypes + "\n" +
                "\t\tAvailability    : " + (isAvailable ? "Available" : "Occupied") + "\n" +
                "\t\tRoom Type       : " + roomType + "\n" +
                "\t\t--------------------------------";
    }

}
