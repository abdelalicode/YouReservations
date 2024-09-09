package com.Youreserve.model;

import java.time.LocalDate;
import java.util.UUID;


public class Reservation {

        private final String reservationID;
        private User user;
        private Room room;
        private LocalDate checkIn;
        private LocalDate checkOut;



        public Reservation(User user, Room room, LocalDate checkIn, LocalDate checkOut) {
                this.user = user;
                this.room = room;
                this.reservationID = UUID.randomUUID().toString();
                this.checkIn = checkIn;
                this.checkOut = checkOut;
        }


        public String getReservationID() {
                return reservationID;
        }

        public  User getUser() {

                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }


        public Room getRoom() {
                return room;
        }

        public void setRoom(Room room) {
                this.room = room;
        }

        public LocalDate getcheckIn() {
                return checkIn;
        }

        public void setCheckIn(LocalDate checkIn) {
                        this.checkIn = checkIn;
        }

        public LocalDate getcheckOut() {
                return checkOut;
        }

        public void setcheckOut(LocalDate checkOut) {
                this.checkOut = checkOut;
        }


        public String toString() {
                return "    Reservation Details:\n" +
                        "-------------------------------------------------\n" +
                        "   Reservation ID    : " + reservationID + "\n" +
                        "   Room Number       : " + room.getRoomNumber() + "\n" +
                        "   Room Type         : " + room.getRoomType() + "\n" +
                        "   Reserved By       : " + user.getName() + "\n" +
                        "   Start Date        : " + checkIn + "\n" +
                        "   End Date          : " + checkOut + "\n" +
                        "---------------------------------------------------";
        }

}
