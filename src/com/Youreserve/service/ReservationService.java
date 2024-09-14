package com.Youreserve.service;

import com.Youreserve.entity.Reservation;
import com.Youreserve.entity.Room;
import com.Youreserve.entity.User;
import com.Youreserve.repository.RepositoryInterface;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

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

        User user = reservationRepository.createUser(clientName, clientEmail);

        System.out.println("Room ID for reservation: " + roomToReserve.getId());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate checkIn = getValidDate(scanner, "Enter check-in date (dd-MM-yyyy): ", dtf);
        LocalDate checkOut = getValidDate(scanner, "Enter check-out date (dd-MM-yyyy): ", dtf);

        if (checkOut.isBefore(checkIn)) {
            System.out.println("Check-out date cannot be before check-in date.");
            return;
        }

        Reservation reservation = new Reservation(user, roomToReserve, checkIn, checkOut);

        double totalPrice = calculateTotalPrice(reservation);

        reservation.setTotalPrice(totalPrice);

        UUID reservationId = reservationRepository.create(reservation);

        if (reservationId != null) {
            System.out.println("Reservation created successfully for room " + roomNumber + ". Reservation ID: " + reservationId);
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


    public void viewReservation(String reservationIdInput) {
        UUID reservationId = UUID.fromString(reservationIdInput);
        Reservation reservation = reservationRepository.findById(reservationId);
        if (reservation != null) {
            System.out.println(reservation);
        } else {
            System.out.println("Reservation not found.");
        }
    }

    public void cancelReservation(Scanner scanner) {
        System.out.println("Enter the Reservation ID to cancel: ");
        String reservationIDInput = scanner.nextLine();
        try {
            UUID reservationID = UUID.fromString(reservationIDInput);
            boolean isDeleted = reservationRepository.deleteById(reservationID);
            if (isDeleted) {
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Reservation not found or could not be cancelled.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid ID format. Please enter a valid ID.");
        }
    }

    public void updateReservation(Scanner scanner) {
        System.out.print("Enter the Reservation ID to update: ");
        String reservationIDInput = scanner.nextLine();
        try {
            UUID reservationID = UUID.fromString(reservationIDInput);

            Reservation existingReservation = reservationRepository.findById(reservationID);

            if (existingReservation == null) {
                System.out.println("Reservation not found.");
                return;
            }

            System.out.println("Current Reservation Details: ");
            System.out.println(existingReservation);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate newCheckIn = getValidDate(scanner, "Enter new check-in date (dd-MM-yyyy): ", dtf);
            LocalDate newCheckOut = getValidDate(scanner, "Enter new check-out date (dd-MM-yyyy): ", dtf);

            if (newCheckOut.isBefore(newCheckIn)) {
                System.out.println("Check-out date cannot be before check-in date.");
                return;
            }

            existingReservation.setCheckIn(newCheckIn);
            existingReservation.setCheckOut(newCheckOut);


            if (reservationRepository.update(existingReservation)) {
                System.out.println("Reservation updated successfully.");
            } else {
                System.out.println("Failed to update reservation.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid ID format. Please enter a valid ID.");
        }
    }


    public double calculateTotalPrice(Reservation reservation) {
        double basePrice = reservation.getRoom().getPrice();
        double totalPrice = basePrice;

        LocalDate checkIn = reservation.getCheckIn();
        LocalDate checkOut = reservation.getCheckOut();

        while (!checkIn.isAfter(checkOut)) {
            if (checkIn.getDayOfWeek() == DayOfWeek.SATURDAY || checkIn.getDayOfWeek() == DayOfWeek.SUNDAY) {
                totalPrice += basePrice * 0.10;
            }

            if (checkIn.getMonth() == Month.JUNE || checkIn.getMonth() == Month.JULY || checkIn.getMonth() == Month.AUGUST) {
                totalPrice += basePrice * 0.15;
            }

            checkIn = checkIn.plusDays(1);
        }

        return totalPrice;
    }


    public void generateReservationReport() {
//        List<Reservation> reservations = reservationRepository.findAllReservations();
//
//        List<Room> reservedRooms = reservations.stream()
//                .map(Reservation::getRoom)
//                .filter(room -> !room.isAvailable())
//                .collect(Collectors.toList());
//
//        long reservedRoomCount = reservedRooms.size();
//
//        double totalRevenue = reservedRooms.stream()
//                .mapToDouble(Room::getPrice) // Extract price
//                .sum();
//
//        System.out.println("Number of reserved rooms: " + reservedRoomCount);
//        System.out.println("Total revenue from reservations: $" + totalRevenue);
    }



}
