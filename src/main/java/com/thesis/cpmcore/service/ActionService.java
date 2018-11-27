package com.thesis.cpmcore.service;

import com.thesis.cpmcore.model.Action;

import java.util.List;

public interface ActionService {

    List<Action> findReservationsForItem(Integer idItem, String type);
    List<Action> findAllReservationsForItem(Integer idItem);
    List<Action> findReservationsForUser(Integer idUser, String type);


}
