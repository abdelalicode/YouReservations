package com.Youreserve.repository;

import com.Youreserve.entity.Reservation;
import com.Youreserve.entity.Room;
import com.Youreserve.entity.User;

import java.util.List;
import java.util.UUID;

public interface RepositoryInterface {

    public UUID create(Reservation reservation);
    public boolean update(Reservation reservation);
    public boolean deleteById(UUID reservationId);
    public List<Room> findAllRooms();
    public Reservation findById(UUID reservationId);
    public List<Reservation> findByDate(String date);
    public Room findRoom(String roomNumber);
    public User createUser(String name, String email);
}
