package com.Youreserve;

import java.util.Random;
import java.util.UUID;


public class Reservation {

        private String reservationID;
        private User user;
        private Room room;



        public Reservation(User user, Room room) {
                this.user = user;
                this.room = room;
                this.reservationID = UUID.randomUUID().toString();
        }


        public int getReservationID() {
                return reservationID;
        }

        public User getUser() {

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

        @Override
        public String toString() {
                return "    Reservation Details:\n" +
                        "-------------------------\n" +
                        "   Reservation ID    : " + reservationID + "\n" +
                        "   Room Number       : " + room.getRoomNumber() + "\n" +
                        "   Room Type         : " + room.getRoomType() + "\n" +
                        "   Reserved By       : " + user.getName() + "\n" +
                        "-------------------------";
        }

}
