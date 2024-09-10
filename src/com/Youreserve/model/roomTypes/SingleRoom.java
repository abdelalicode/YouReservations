package com.Youreserve.model.roomTypes;

import com.Youreserve.enums.BedType;
import com.Youreserve.model.Room;

public class SingleRoom extends Room {
    private int id;
    private BedType bedType;
    private boolean hasWorkspace;

    public SingleRoom(String roomNumber, String roomType, int id , BedType bedType, boolean hasWorkspace) {
        super(roomNumber, roomType);
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
        return "SingleRoom{" +
                "bedType=" + bedType +
                ", hasWorkspace=" + hasWorkspace +
                ", isAvailable=" + isAvailable +
                ", roomType='" + roomType + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                '}';
    }
}
