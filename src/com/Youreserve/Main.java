package com.Youreserve;

import com.Youreserve.repository.RepositoryInterface;
import com.Youreserve.repository.classes.ReservationRepository;
import com.Youreserve.service.ReservationService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        RepositoryInterface reservationRepository = new ReservationRepository();

        ReservationService service = new ReservationService(reservationRepository);
        Scanner scanner = new Scanner(System.in);

        do {
            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    service.viewAllRooms();
                    int option = chooseOption();
                    if (option == 1) {
                        service.createReservation(scanner);
                    }
                    pause(scanner);
                    break;
                case 2:
                    System.out.println("Enter reservation id: ");
                    service.viewReservation(scanner.nextLine());
                    pause(scanner);
                    break;
                case 3:
                    service.cancelReservation(scanner);
                    pause(scanner);
                    break;
                case 4:
                    service.updateReservation(scanner);
                    pause(scanner);
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
        System.out.println("2. View Reservation Details");
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
            scanner.nextLine();
        }
        return choice;
    }

    private static void pause(Scanner scanner) {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
        scanner.nextLine();
    }

    public static int chooseOption() {

        System.out.println("1 . Do you want to reserve a room?");
        System.out.println("0 . Go back to main menu");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();


        return option;
    }
}
