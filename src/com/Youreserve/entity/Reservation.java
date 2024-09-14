package com.Youreserve.entity;

import java.time.LocalDate;
import java.util.UUID;


public class Reservation {

        private UUID reservationID;
        private User user;
        private Room room;
        private double totalPrice;
        private LocalDate checkIn;
        private LocalDate checkOut;

        public Reservation(User user, Room room, LocalDate checkIn, LocalDate checkOut) {
                this.user = user;
                this.room = room;
                this.checkIn = checkIn;
                this.checkOut = checkOut;
        }

        public Reservation(UUID reservationID, User user, Room room, double totalPrice, LocalDate checkIn, LocalDate checkOut) {
                this.reservationID = reservationID;
                this.user = user;
                this.room = room;
                this.totalPrice = totalPrice;
                this.checkIn = checkIn;
                this.checkOut = checkOut;
        }

        public UUID getReservationID() {
                return reservationID;
        }

        public void setReservationID(UUID reservationID) {
                this.reservationID = reservationID;
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

        public double getTotalPrice() {
                return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
                this.totalPrice = totalPrice;
        }

        public LocalDate getCheckIn() {
                return checkIn;
        }

        public void setCheckIn(LocalDate checkIn) {
                this.checkIn = checkIn;
        }

        public LocalDate getCheckOut() {
                return checkOut;
        }

        public void setCheckOut(LocalDate checkOut) {
                this.checkOut = checkOut;
        }

        @Override
        public String toString() {
                return  "======================= Reservation Details =======================\n" +
                        "   Reservation ID : " + reservationID + "\n" +
                        "-------------------------------------------------------------------\n" +
                        "   Room Information:\n" +
                        "   - Room Number   : " + room.getRoomNumber() + "\n" +
                        "   - Room Type     : " + room.getRoomType() + "\n" +
                        "-------------------------------------------------------------------\n" +
                        "   Guest Information:\n" +
                        "   - Reserved By   : " + user.getName() + "\n" +
                        "-------------------------------------------------------------------\n" +
                        "   Reservation Dates:\n" +
                        "   - Start Date    : " + checkIn + "\n" +
                        "   - End Date      : " + checkOut + "\n" +
                        "   - Total Price   : " + totalPrice + "\n" +
                        "===================================================================";
        }
}
