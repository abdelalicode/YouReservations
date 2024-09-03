package com.Youreserve;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Room> rooms = new ArrayList<>();


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        rooms.add(new Room("5A", "Single"));
        rooms.add(new Room("5B", "Single"));
        rooms.add(new Room("6A", "Double"));
        rooms.add(new Room("6B", "Double"));
        rooms.add(new Room("SS", "Suite"));


        while (true) {
            System.out.println("\tHotel Reservation System");
            System.out.println("1. View Rooms");
            System.out.println("2. View Reservations");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("View Rooms");
                    listRooms();
                    int option = ReserveRoom.chooseOption();
                    if (option == 1) {
                        ReserveRoom.createReservation(scanner);
                    }
                    break;
                case 2:
                    System.out.println("View Reservations");
                    ReserveRoom.listReservations();
                    break;
                case 3:
                    System.out.println("Cancel Reservation");
                    break;
                case 4:
                    System.out.println("Exit");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }



    }
    private static void listRooms() {
        for (Room room : rooms) {
            System.out.println(room);
        }


    }
}
