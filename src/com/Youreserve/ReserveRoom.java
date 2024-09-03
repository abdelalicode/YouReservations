package com.Youreserve;

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

        Reservation reservation = new Reservation(user, roomToReserve);
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
}
