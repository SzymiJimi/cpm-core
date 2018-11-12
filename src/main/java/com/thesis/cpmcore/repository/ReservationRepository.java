package com.thesis.cpmcore.repository;

import com.thesis.cpmcore.model.Item;
import com.thesis.cpmcore.model.Reservation;
import com.thesis.cpmcore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findReservationsByReserverUserAndType(User reserverUser, String type);
    List<Reservation> findReservationsByItemIdAndType(Item itemId, String type);
    List<Reservation> findReservationsByItemId(Item itemId);

}
