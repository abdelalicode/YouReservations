package com.Youreserve.entity.roomTypes;

import com.Youreserve.enums.BedType;
import com.Youreserve.entity.Room;

public class DoubleRoom extends Room {

    private int id;
    private int numberBeds;
    private BedType bedtypes;


    public DoubleRoom(String roomNumber, String roomType, double price , int id , int numberBeds, BedType bedtypes) {
        super(roomNumber, roomType , price);
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
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {

        return  "\t\t----------------------------------------------"+"\n" +
                "\u001B[31m\t\tROOM TYPE   : " + roomType.toUpperCase() + "\t\tPrice : " + price + "MAD" + "\n\u001B[0m"+
                "\t\t----------------------------------------------"+"\n" +
                "\t\tRoom Number     : " + roomNumber + "\n" +
                "\t\tNumber of Beds  : " + numberBeds + "\n" +
                "\t\tBed Types       : " + bedtypes + "\n" +
                "\t\tAvailability    : " + (isAvailable ? "Available" : "Occupied") + "\n" +
                "\t\t-----------------------------------------------";
    }

}
