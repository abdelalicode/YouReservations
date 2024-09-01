package com.Youreserve;

import javax.swing.*;

public class Reservation {

        private User user;
        private Room room;

        public Reservation(User user, Room room) {
                this.user = user;
                this.room = room;
        }

        public User getUser() {
                return user;
        }

        public Room getRoom() {
                return room;
        }

        public String toString() {
                return "Reservation : room=" + room.getRoomNumber() + " }";
        }

}
