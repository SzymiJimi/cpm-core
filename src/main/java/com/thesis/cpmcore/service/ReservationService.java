package com.thesis.cpmcore.service;

import com.thesis.cpmcore.model.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> findReservationsForItem(Integer idItem, String type);
    List<Reservation> findAllReservationsForItem(Integer idItem);
    List<Reservation> findReservationsForUser(Integer idUser, String type);


}
