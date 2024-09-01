package com.Youreserve;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Reservation> reservations = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("\tHotel Reservation System");
            System.out.println("1. Create Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. View Rooms");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("View Rooms");
                    break;
                case 2:
                    System.out.println("View Reservations");
                    break;
                case 3:
                    System.out.println("Cancel Reservation");
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Exit");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }

    }
}
