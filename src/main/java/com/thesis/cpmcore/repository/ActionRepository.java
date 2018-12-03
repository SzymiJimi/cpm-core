package com.thesis.cpmcore.repository;

import com.thesis.cpmcore.model.Item;
import com.thesis.cpmcore.model.Action;
import com.thesis.cpmcore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Integer> {

    List<Action> findReservationsByReserverUserAndType(User reserverUser, String type);
    List<Action> findReservationsByItemIdAndType(Item itemId, String type);
    Action findReservationByIdReservationAndType(Integer id, String type);
    Action findReservationByIdReservation(Integer id);
    List<Action> findReservationsByItemId(Item itemId);
    List<Action> findReservationsByReserverUserIdUser(Integer idUser);
}
