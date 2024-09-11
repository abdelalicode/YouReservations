package com.Youreserve.repository.classes;

import com.Youreserve.enums.BedType;
import com.Youreserve.enums.LuxuryLevel;
import com.Youreserve.model.Reservation;
import com.Youreserve.model.Room;
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

        return false;
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
                " LEFT JOIN single_rooms ON rooms.room_id = single_rooms.room_id " +
                "LEFT JOIN double_rooms ON rooms.room_id = double_rooms.room_id " +
                "LEFT JOIN suite_rooms ON rooms.room_id = suite_rooms.room_id";

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
                                rs.getInt("room_id"),
                                BedType.valueOf(rs.getString("bed_type")),
                                rs.getBoolean("has_workspace")
                        );
                        break;
                    case "Double":
                        room = new DoubleRoom(
                                rs.getString("room_number"),
                                roomType,
                                rs.getInt("room_id"),
                                rs.getInt("number_of_beds"),
                                BedType.valueOf(rs.getString("Dbed_type"))
                        );
                        break;

                    case "Suite":
                        room = new SuiteRoom(
                                rs.getString("room_number"),
                                roomType,
                                rs.getInt("room_id"),
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
        String query = "SELECT * FROM rooms " +
                "LEFT JOIN single_rooms ON rooms.room_id = single_rooms.room_id " +
                "LEFT JOIN double_rooms ON rooms.room_id = double_rooms.room_id " +
                "LEFT JOIN suite_rooms ON rooms.room_id = suite_rooms.room_id " +
                "WHERE room_number = ? AND is_available = TRUE";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, roomNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return oneRowRoom(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding room: " + e.getMessage());
        }
        return null;
    }

    private Room oneRowRoom(ResultSet rs) throws SQLException {
        String roomNumber = rs.getString("room_number");
        String roomType = rs.getString("room_type");
        int roomId = rs.getInt("room_id");
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
                        BedType.valueOf(rs.getString("bed_type"))
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



}
