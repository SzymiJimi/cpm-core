package com.thesis.cpmcore.repository;

import com.thesis.cpmcore.model.Reservation;
import com.thesis.cpmcore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findReservationsByReserverUser(User reserverUser);

}
