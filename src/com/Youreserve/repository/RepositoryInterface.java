package com.Youreserve.repository;

import com.Youreserve.model.Reservation;

import java.util.List;

public interface RepositoryInterface {

    public boolean insert(Reservation reservation);
    public boolean update(Reservation reservation);
    public boolean delete(Reservation reservation);
    public List<Reservation> findAll();
    public Reservation findById(int id);
    public List<Reservation> findByDate(String date);

}
