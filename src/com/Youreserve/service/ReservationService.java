package com.Youreserve.service;

import com.Youreserve.model.Reservation;
import com.Youreserve.model.Room;
import com.Youreserve.enums.BedType;
import com.Youreserve.enums.LuxuryLevel;
import com.Youreserve.model.User;
import com.Youreserve.model.roomTypes.DoubleRoom;
import com.Youreserve.model.roomTypes.SingleRoom;
import com.Youreserve.model.roomTypes.SuiteRoom;
import com.Youreserve.repository.RepositoryInterface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        System.out.println("Please enter the room number you would like to reserve:");
        String roomNumber = scanner.nextLine();

        Room roomToReserve = reservationRepository.findRoom(roomNumber);

        if (roomToReserve == null || !roomToReserve.isAvailable()) {
            System.out.println("Room not available or does not exist.");
            return;
        }

        System.out.println("Please enter the name of the person to reserve for:");
        String clientName = scanner.nextLine();
        System.out.println("Please enter the email of the person to reserve for:");
        String clientEmail = scanner.nextLine();

//        User user = new User(clientName, clientEmail);
        User user = reservationRepository.createUser(clientName, clientEmail);

        System.out.println(user);
        System.out.println("Room ID for reservation: " + roomToReserve.getId());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate checkIn = getValidDate(scanner, "Enter check-in date (dd-MM-yyyy): ", dtf);
        LocalDate checkOut = getValidDate(scanner, "Enter check-out date (dd-MM-yyyy): ", dtf);

        if (checkOut.isBefore(checkIn)) {
            System.out.println("Check-out date cannot be before check-in date.");
            return;
        }

        Reservation reservation = new Reservation(user, roomToReserve, checkIn, checkOut);


        if (reservationRepository.create(reservation)) {
            System.out.println("Reservation created for room " + roomNumber);
        } else {
            System.out.println("Failed to create reservation.");
        }
    }

    private LocalDate getValidDate(Scanner scanner, String prompt, DateTimeFormatter dtf) {
        LocalDate date = null;
        while (date == null) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                date = LocalDate.parse(input, dtf);
                if (date.isBefore(LocalDate.now())) {
                    System.out.println("Date is in the past. Please enter a future date.");
                    date = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use dd-MM-yyyy format.");
            }
        }
        return date;
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
