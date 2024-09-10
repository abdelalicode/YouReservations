package com.Youreserve;

import com.Youreserve.service.ReservationService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ReservationService service = new ReservationService();
        Scanner scanner = new Scanner(System.in);

        do {
            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    service.viewRooms();
                    service.reserveRoom(scanner);
                    break;
                case 2:
                    service.viewReservations();
                    break;
                case 3:
                    service.cancelReservation(scanner);
                    break;
                case 4:
                    service.updateReservation(scanner);
                    break;
                case 5:
                    System.out.println("Exit");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

    private static void displayMenu() {
        System.out.println("\tHotel Reservation System");
        System.out.println("1. View Rooms");
        System.out.println("2. View Reservations");
        System.out.println("3. Cancel Reservation");
        System.out.println("4. Update Reservation");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static int getUserChoice(Scanner scanner) {
        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } finally {
            scanner.nextLine(); // Clear the buffer
        }
        return choice;
    }
}
