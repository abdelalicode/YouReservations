package com.Youreserve.repository.classes;

import com.Youreserve.enums.BedType;
import com.Youreserve.enums.LuxuryLevel;
import com.Youreserve.model.Reservation;
import com.Youreserve.model.Room;
import com.Youreserve.model.User;
import com.Youreserve.model.roomTypes.DoubleRoom;
import com.Youreserve.model.roomTypes.SingleRoom;
import com.Youreserve.model.roomTypes.SuiteRoom;
import com.Youreserve.repository.RepositoryInterface;
import ressources.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository implements RepositoryInterface {

    private static Connection conn = DBconnection.getInstance().getConnection();

    @Override
    public boolean create(Reservation reservation) {
        String insertReservationQuery = "INSERT INTO reservations (user_id, room_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
        String updateRoomQuery = "UPDATE rooms SET is_available = FALSE WHERE id = ?";

        try (PreparedStatement insertStmt = conn.prepareStatement(insertReservationQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateRoomQuery)) {

            insertStmt.setInt(1, reservation.getUser().getId());
            insertStmt.setInt(2, reservation.getRoom().getId());
            insertStmt.setDate(3, java.sql.Date.valueOf(reservation.getcheckIn()));
            insertStmt.setDate(4, java.sql.Date.valueOf(reservation.getcheckOut()));
            int rowsInserted = insertStmt.executeUpdate();

            updateStmt.setInt(1, reservation.getRoom().getId());
            int rowsUpdated = updateStmt.executeUpdate();

            return rowsInserted > 0 && rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Error creating reservation: " + e.getMessage());
            return false;
        }
    }


    @Override
    public boolean update(Reservation reservation) {
        return false;
    }

    @Override
    public boolean delete(Reservation reservation) {

        return false;
    }

    @Override
    public List<Reservation> findAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
//        try {
//            PreparedStatement st = conn.prepareStatement("SELECT * FROM reservations");
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                // Fetch data from result set and add to the list
//                // reservations.add(new Reservation(...));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return reservations;
    }

    public Reservation findById(int id) {
        // Implement the SQL SELECT by ID logic here
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
                Room room;

                switch (roomType) {
                    case "Single":
                        room = new SingleRoom(
                                rs.getString("room_number"),
                                roomType,
                                rs.getInt("id"),
                                BedType.valueOf(rs.getString("bed_type")),
                                rs.getBoolean("has_workspace")
                        );
                        break;
                    case "Double":
                        room = new DoubleRoom(
                                rs.getString("room_number"),
                                roomType,
                                rs.getInt("id"),
                                rs.getInt("number_of_beds"),
                                BedType.valueOf(rs.getString("dbed_type"))
                        );
                        break;

                    case "Suite":
                        room = new SuiteRoom(
                                rs.getString("room_number"),
                                roomType,
                                rs.getInt("id"),
                                LuxuryLevel.valueOf(rs.getString("luxury_level")),
                                rs.getBoolean("has_balcony")
                        );
                        break;
                    default:
                        room = new Room(rs.getString("room_number"), roomType);
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


    private Room oneRoom(ResultSet rs) throws SQLException {
        String roomNumber = rs.getString("room_number");
        String roomType = rs.getString("room_type");
        int roomId = rs.getInt("id");
        boolean isAvailable = rs.getBoolean("is_available");

        switch (roomType) {
            case "Single":
                return new SingleRoom(
                        roomNumber,
                        roomType,
                        roomId,
                        BedType.valueOf(rs.getString("bed_type")),
                        rs.getBoolean("has_workspace")
                );
            case "Double":
                return new DoubleRoom(
                        roomNumber,
                        roomType,
                        roomId,
                        rs.getInt("number_of_beds"),
                        BedType.valueOf(rs.getString("dbed_type"))
                );
            case "Suite":
                return new SuiteRoom(
                        roomNumber,
                        roomType,
                        roomId,
                        LuxuryLevel.valueOf(rs.getString("luxury_level")),
                        rs.getBoolean("has_balcony")
                );
            default:
                return new Room(roomNumber, roomType);
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




}
