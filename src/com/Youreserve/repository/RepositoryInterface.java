package com.Youreserve.repository;

import com.Youreserve.model.Reservation;
import com.Youreserve.model.Room;
import com.Youreserve.model.User;

import java.util.List;

public interface RepositoryInterface {

    public boolean create(Reservation reservation);
    public boolean update(Reservation reservation);
    public boolean delete(Reservation reservation);
    public List<Reservation> findAllReservations();
    public List<Room> findAllRooms();
    public Reservation findById(int id);
    public List<Reservation> findByDate(String date);
    public Room findRoom(String roomNumber);
    public User createUser(String name, String email);
}
