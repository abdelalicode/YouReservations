package com.Youreserve.repository.classes;

import com.Youreserve.model.Reservation;
import com.Youreserve.repository.RepositoryInterface;
import ressources.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository implements RepositoryInterface {

    private static Connection conn = DBconnection.getInstance().getConnection();

    @Override
    public boolean insert(Reservation reservation) {
        // Implement the SQL INSERT logic here
        return false;
    }

    @Override
    public boolean update(Reservation reservation) {
        // Implement the SQL UPDATE logic here
        return false;
    }

    @Override
    public boolean delete(Reservation reservation) {
        // Implement the SQL DELETE logic here
        return false;
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM reservations");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                // Fetch data from result set and add to the list
                // reservations.add(new Reservation(...));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public Reservation findById(int id) {
        // Implement the SQL SELECT by ID logic here
        return null;
    }

    @Override
    public List<Reservation> findByDate(String date) {
        // Implement the SQL SELECT by date logic here
        return null;
    }
}
