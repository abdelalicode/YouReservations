package com.Youreserve.repository.classes;

import com.Youreserve.enums.BedType;
import com.Youreserve.enums.LuxuryLevel;
import com.Youreserve.entity.Reservation;
import com.Youreserve.entity.Room;
import com.Youreserve.entity.User;
import com.Youreserve.entity.roomTypes.DoubleRoom;
import com.Youreserve.entity.roomTypes.SingleRoom;
import com.Youreserve.entity.roomTypes.SuiteRoom;
import com.Youreserve.repository.RepositoryInterface;
import ressources.DBconnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservationRepository implements RepositoryInterface {

    private static Connection conn = DBconnection.getInstance().getConnection();

    public UUID create(Reservation reservation) {
        String insertReservationQuery = "INSERT INTO reservations (user_id, room_id, check_in_date, check_out_date, total_price) VALUES (?, ?, ?, ?, ?) RETURNING reservation_id";
        String updateRoomQuery = "UPDATE rooms SET is_available = FALSE WHERE id = ?";

        try (PreparedStatement insertStmt = conn.prepareStatement(insertReservationQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateRoomQuery)) {

            insertStmt.setInt(1, reservation.getUser().getId());
            insertStmt.setInt(2, reservation.getRoom().getId());
            insertStmt.setDate(3, java.sql.Date.valueOf(reservation.getCheckIn()));
            insertStmt.setDate(4, java.sql.Date.valueOf(reservation.getCheckOut()));
            insertStmt.setDouble(5, reservation.getTotalPrice());

            try (ResultSet rs = insertStmt.executeQuery()) {
                if (rs.next()) {
                    UUID reservationId = (UUID) rs.getObject("reservation_id");

                    updateStmt.setInt(1, reservation.getRoom().getId());
                    int rowsUpdated = updateStmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        return reservationId;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error creating reservation: " + e.getMessage());
        }

        return null;
    }


    public boolean update(Reservation reservation) {
        String updateQuery = String.format(
                "UPDATE reservations SET check_in_date = '%s', check_out_date = '%s' WHERE reservation_id = '%s'",
                java.sql.Date.valueOf(reservation.getCheckIn()),
                java.sql.Date.valueOf(reservation.getCheckOut()),
                reservation.getReservationID().toString()
        );

        try (Statement stmt = conn.createStatement()) {
            int rowsUpdated = stmt.executeUpdate(updateQuery);

            if (rowsUpdated > 0) {
                System.out.println("Reservation updated successfully.");
                return true;
            } else {
                System.out.println("No reservation found with the given ID.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error updating reservation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteById(UUID reservationId) {
        String deleteQuery = "DELETE FROM reservations WHERE reservation_id = ?";
        String updateRoomQuery = "UPDATE rooms SET is_available = TRUE WHERE id = ?";

        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateRoomQuery)) {

            Reservation reservation = findById(reservationId);
            if (reservation == null) {
                System.out.println("Reservation not found.");
                return false;
            }
            int roomId = reservation.getRoom().getId();

            deleteStmt.setObject(1, reservationId, java.sql.Types.OTHER);
            int rowsDeleted = deleteStmt.executeUpdate();

            updateStmt.setInt(1, roomId);
            int rowsUpdated = updateStmt.executeUpdate();

            return rowsDeleted > 0 && rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Error cancelling reservation: " + e.getMessage());
            return false;
        }
    }


    public Reservation findById(UUID reservationId) {
        String query = "SELECT * FROM reservations WHERE reservation_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setObject(1, reservationId, Types.OTHER);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    UUID id = (UUID) rs.getObject("reservation_id");
                    int userId = rs.getInt("user_id");
                    int roomId = rs.getInt("room_id");
                    LocalDate checkIn = rs.getDate("check_in_date").toLocalDate();
                    LocalDate checkOut = rs.getDate("check_out_date").toLocalDate();
                    double totalPrice = rs.getDouble("total_price");

                    User user = findUserById(userId);
                    Room room = findRoomById(roomId);

                    if (user != null && room != null) {
                        return new Reservation(id, user, room, totalPrice, checkIn, checkOut);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding reservation by ID: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Reservation> findByDate(String date) {
        return null;
    }

    public List<Room> findAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms" +
                " LEFT JOIN single_rooms ON rooms.id = single_rooms.id " +
                "LEFT JOIN double_rooms ON rooms.id = double_rooms.id " +
                "LEFT JOIN suite_rooms ON rooms.id = suite_rooms.id";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String roomType = rs.getString("room_type");
                double price = rs.getDouble("price");
                Room room;

                switch (roomType) {
                    case "Single":
                        room = new SingleRoom(
                                rs.getString("room_number"),
                                roomType,
                                price,
                                rs.getInt("id"),
                                BedType.valueOf(rs.getString("bed_type")),
                                rs.getBoolean("has_workspace")
                        );
                        break;
                    case "Double":
                        room = new DoubleRoom(
                                rs.getString("room_number"),
                                roomType,
                                price,
                                rs.getInt("id"),
                                rs.getInt("number_of_beds"),
                                BedType.valueOf(rs.getString("dbed_type"))
                        );
                        break;

                    case "Suite":
                        room = new SuiteRoom(
                                rs.getString("room_number"),
                                roomType,
                                price,
                                rs.getInt("id"),
                                LuxuryLevel.valueOf(rs.getString("luxury_level")),
                                rs.getBoolean("has_balcony")
                        );
                        break;
                    default:
                        room = new Room(rs.getString("room_number"), roomType , price);
                }

                room.setAvailable(rs.getBoolean("is_available"));
                rooms.add(room);
            }
        } catch (Exception e) {
            System.out.println("Error fetching rooms: " + e.getMessage());
        }

        return rooms;
    }


    public Room findRoom(String roomNumber) {
        String query = "SELECT rooms.room_number, rooms.room_type, rooms.id, rooms.is_available, " +
                "rooms.price, " +
                "single_rooms.bed_type, single_rooms.has_workspace, " +
                "double_rooms.number_of_beds, double_rooms.dbed_type AS dbed_type, " +
                "suite_rooms.luxury_level, suite_rooms.has_balcony " +
                "FROM rooms " +
                "LEFT JOIN single_rooms ON rooms.id = single_rooms.id " +
                "LEFT JOIN double_rooms ON rooms.id = double_rooms.id " +
                "LEFT JOIN suite_rooms ON rooms.id = suite_rooms.id " +
                "WHERE rooms.room_number = ? AND rooms.is_available = TRUE";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, roomNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return oneRoom(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding room: " + e.getMessage());
        }
        return null;
    }


    public Room findRoomById(int roomId) {
        String query = "SELECT rooms.id AS id, rooms.room_number, rooms.room_type, rooms.is_available, " +
                "rooms.price, " +
                "single_rooms.bed_type, single_rooms.has_workspace, " +
                "double_rooms.number_of_beds, double_rooms.dbed_type AS dbed_type, " +
                "suite_rooms.luxury_level, suite_rooms.has_balcony " +
                "FROM rooms " +
                "LEFT JOIN single_rooms ON rooms.id = single_rooms.id " +
                "LEFT JOIN double_rooms ON rooms.id = double_rooms.id " +
                "LEFT JOIN suite_rooms ON rooms.id = suite_rooms.id " +
                "WHERE rooms.id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, roomId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return oneRoom(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding room by ID: " + e.getMessage());
        }
        return null;
    }


    private Room oneRoom(ResultSet rs) throws SQLException {
        String roomNumber = rs.getString("room_number");
        String roomType = rs.getString("room_type");
        int roomId = rs.getInt("id");
        boolean isAvailable = rs.getBoolean("is_available");
        double price = rs.getDouble("price");  // Retrieve the price

        switch (roomType) {
            case "Single":
                return new SingleRoom(
                        roomNumber,
                        roomType,
                        price,
                        roomId,
                        BedType.valueOf(rs.getString("bed_type")),
                        rs.getBoolean("has_workspace")
                );

            case "Double":
                return new DoubleRoom(
                        roomNumber,
                        roomType,
                        price,
                        roomId,
                        rs.getInt("number_of_beds"),
                        BedType.valueOf(rs.getString("dbed_type"))

                );
            case "Suite":
                return new SuiteRoom(
                        roomNumber,
                        roomType,
                        price,
                        roomId,
                        LuxuryLevel.valueOf(rs.getString("luxury_level")),
                        rs.getBoolean("has_balcony")

                );
            default:
                return new Room(roomNumber, roomType, price);
        }
    }


    public User createUser(String name, String email) {
        String insertUserQuery = "INSERT INTO users (name, email) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = conn.prepareStatement(insertUserQuery)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("id");
                    System.out.println(userId);
                    return new User(userId, name, email);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
        return null;
    }

    public User findUserById(int userId) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    return new User(id, name, email);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding user by ID: " + e.getMessage());
        }
        return null;
    }




}
