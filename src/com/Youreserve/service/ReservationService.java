package com.Youreserve.service;

import com.Youreserve.model.Room;
import com.Youreserve.enums.BedType;
import com.Youreserve.enums.LuxuryLevel;
import com.Youreserve.model.roomTypes.DoubleRoom;
import com.Youreserve.model.roomTypes.SingleRoom;
import com.Youreserve.model.roomTypes.SuiteRoom;
import com.Youreserve.repository.RepositoryInterface;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ReservationService {

    private final RepositoryInterface reservationRepository;

    public ReservationService(RepositoryInterface reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void viewAllRooms() {
        List<Room> rooms = reservationRepository.findAllRooms();
        rooms.stream()
                .sorted(Comparator.comparing(Room::getRoomNumber))
                .forEach(System.out::println);

    }

    public void createReservation(Scanner scanner) {
        System.out.println("Reserve a Room");


    }
//
//    public void viewReservations() {
//        // Fetch reservations from the repository
//        reservationRepository.findAll().forEach(System.out::println);
//    }
//
//    public void cancelReservation(Scanner scanner) {
//        System.out.print("Enter the Reservation ID to cancel: ");
//        String reservationID = scanner.nextLine();
//        // Implement cancellation logic
//        // reservationRepository.deleteById(Integer.parseInt(reservationID));
//    }
//
//    public void updateReservation(Scanner scanner) {
//        System.out.print("Enter the Reservation ID to update: ");
//        String reservationID = scanner.nextLine();
//        // Update reservation details in the repository
//        // reservationRepository.update(...);
//    }





}
