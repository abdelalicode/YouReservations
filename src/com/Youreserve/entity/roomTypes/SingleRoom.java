package com.Youreserve.entity.roomTypes;

import com.Youreserve.enums.BedType;
import com.Youreserve.entity.Room;

public class SingleRoom extends Room {
    private int id;
    private BedType bedType;
    private boolean hasWorkspace;

    public SingleRoom(String roomNumber, String roomType, double price , int id , BedType bedType, boolean hasWorkspace) {
        super(roomNumber, roomType , price);
        this.id = id;
        this.bedType = bedType;
        this.hasWorkspace = hasWorkspace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BedType getBedType() {
        return bedType;
    }

    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }

    public boolean isHasWorkspace() {
        return hasWorkspace;
    }

    public void setHasWorkspace(boolean hasWorkspace) {
        this.hasWorkspace = hasWorkspace;
    }


    @Override
    public String toString() {
        return  "\t\t-----------------------------------------------"+"\n" +
                "\u001B[34m\t\tROOM TYPE   : " + roomType.toUpperCase() + "\t\tPrice : " + price + "MAD" + "\n\u001B[0m"+
                "\t\t-----------------------------------------------"+"\n" +
                "\t\tRoom Number     : " + roomNumber + "\n" +
                "\t\tBed Type        : " + bedType + "\n" +
                "\t\tHas Workspace   : " + (hasWorkspace ? "Yes" : "No") + "\n" +
                "\t\tAvailability    : " + (isAvailable ? "Available" : "Occupied") + "\n" +
                "\t\t-----------------------------------------------";
    }

}
