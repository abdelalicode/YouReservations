package com.Youreserve.service;

import com.Youreserve.model.Room;
import com.Youreserve.repository.RepositoryInterface;
import com.Youreserve.repository.classes.ReservationRepository;

import java.util.List;
import java.util.Scanner;

public class ReservationService {

    private RepositoryInterface reservationRepository = new ReservationRepository();

    public void viewRooms() {
        List<Room> rooms = List.of(
                new Room("5A", "Single"),
                new Room("5B", "Single"),
                new Room("6A", "Double"),
                new Room("6B", "Double"),
                new Room("SS", "Suite")
        );

        rooms.forEach(System.out::println);
    }

    public void reserveRoom(Scanner scanner) {
        System.out.println("Reserve a Room");
        // Implement reservation logic here
        // Possibly ask the user for details and call repository methods
    }

    public void viewReservations() {
        // Fetch reservations from the repository
        reservationRepository.findAll().forEach(System.out::println);
    }

    public void cancelReservation(Scanner scanner) {
        System.out.print("Enter the Reservation ID to cancel: ");
        String reservationID = scanner.nextLine();
        // Fetch and delete reservation from the repository using the ID
        // reservationRepository.deleteById(Integer.parseInt(reservationID));
    }

    public void updateReservation(Scanner scanner) {
        System.out.print("Enter the Reservation ID to update: ");
        String reservationID = scanner.nextLine();
        // Update reservation details in the repository
        // reservationRepository.update(...);
    }
}
