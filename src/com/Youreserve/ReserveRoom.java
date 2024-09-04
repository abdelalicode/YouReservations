package com.Youreserve;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ReserveRoom {

    static ArrayList<Reservation> reservations = new ArrayList<>();

    public static int chooseOption() {

        System.out.println("1 . Do you want to reserve a room?");
        System.out.println("0 . Go back to main menu");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();


        return option;
    }

    public static void createReservation(Scanner scanner) {
        System.out.println("Please enter the room number you would like to reserve:");
        scanner.nextLine();
        String roomNumber = scanner.nextLine();

        Room roomToReserve = findRoom(roomNumber);

        if (roomToReserve == null || !roomToReserve.isAvailable()) {
            System.out.println("Room not available or does not exist.");
            return;
        }

        System.out.println("Please enter the name of the person to reserve for:");
        String clientName = scanner.nextLine();

        User user = new User(clientName);

        System.out.print("Enter start date (yyyy-MM-dd): ");
        String startDate = scanner.nextLine();

        System.out.print("Enter end date (yyyy-MM-dd): ");
        String endDate = scanner.nextLine();

        Reservation reservation = new Reservation(user, roomToReserve, startDate, endDate);
        reservations.add(reservation);

        roomToReserve.setAvailable(false);

        System.out.println("Reservation created for room " + roomNumber);
    }

    public static Room findRoom(String roomNumber) {
        for (Room room : Main.rooms) {
            if (room.getRoomNumber().equalsIgnoreCase(roomNumber)) {
                return room;
            }
        }
        return null;
    }


    public static void listReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    public static boolean cancelReservation(String reservationID) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationID().equals(reservationID)) {
                reservations.remove(reservation);

                Room room = reservation.getRoom();
                room.setAvailable(true);

                System.out.println("Reservation with ID " + reservationID + " has been canceled");
                return true;
            }
        }
        System.out.println("No reservation found with ID " + reservationID);
        return false;
    }

    public static boolean updateReservation(String reservationID, Scanner scanner) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationID().equals(reservationID)) {
                System.out.println("Reservation found: ");
                System.out.println(reservation);

                System.out.println("What would you like to update?");
                System.out.println("1. Change Room");
                System.out.println("2. Change Person");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        //Room
                        System.out.print("Enter the new room number: ");
                        String newRoomNumber = scanner.nextLine();
                        Room newRoom = findRoom(newRoomNumber);
                        if (newRoom != null && newRoom.isAvailable()) {
                            reservation.getRoom().setAvailable(true);
                            newRoom.setAvailable(false);
                            reservation.setRoom(newRoom);
                            System.out.println("Room updated successfully.");
                        } else {
                            System.out.println("Room is not available or does not exist.");
                        }
                        break;
                    case 2:
                        //Person
                        System.out.print("Enter the new person's name: ");
                        String newName = scanner.nextLine();
                        reservation.getUser().setName(newName);
                        System.out.println("Person updated successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        return false;
                }
                return true;
            }
        }
        System.out.println("No reservation found with ID " + reservationID);
        return false;
    }
}
