package com.Youreserve;

import java.util.UUID;


public class Reservation {

        private final String reservationID;
        private User user;
        private Room room;
        private String startDate;
        private String endDate;



        public Reservation(User user, Room room, String startDate, String endDate) {
                this.user = user;
                this.room = room;
                this.reservationID = UUID.randomUUID().toString();
                this.startDate = startDate;
                this.endDate = endDate;
        }


        public String getReservationID() {
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

        public String getStartDate() {
                return startDate;
        }

        public void setStartDate(String startDate) {
                if (isValidDate(startDate)) {
                        this.startDate = startDate;
                } else {
                        System.out.println("Invalid start date format yyyy-MM-dd.");
                }
        }

        public String getEndDate() {
                return endDate;
        }

        public void setEndDate(String endDate) {
                if (isValidDate(endDate)) {
                        this.endDate = endDate;
                } else {
                        System.out.println("Invalid end date format yyyy-MM-dd.");
                }
        }

        private boolean isValidDate(String date) {
                date = date.trim();
                String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";
                return date.matches(datePattern);
        }


        public String toString() {
                return "    Reservation Details:\n" +
                        "-------------------------------------------------\n" +
                        "   Reservation ID    : " + reservationID + "\n" +
                        "   Room Number       : " + room.getRoomNumber() + "\n" +
                        "   Room Type         : " + room.getRoomType() + "\n" +
                        "   Reserved By       : " + user.getName() + "\n" +
                        "   Start Date        : " + startDate + "\n" +
                        "   End Date          : " + endDate + "\n" +
                        "---------------------------------------------------";
        }

}
