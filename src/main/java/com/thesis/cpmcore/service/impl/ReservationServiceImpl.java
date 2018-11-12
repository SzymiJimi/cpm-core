package com.thesis.cpmcore.service.impl;

import com.thesis.cpmcore.model.Item;
import com.thesis.cpmcore.model.Reservation;
import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.ItemRepository;
import com.thesis.cpmcore.repository.ReservationRepository;
import com.thesis.cpmcore.repository.UserRepository;
import com.thesis.cpmcore.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {


    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                              UserRepository userRepository,
                              ItemRepository itemRepository){
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Reservation> findReservationsForItem(Integer idItem, String type) {
        Item item = itemRepository.findById(idItem).get();
        return reservationRepository.findReservationsByItemIdAndType(item, type);
    }

    @Override
    public List<Reservation> findAllReservationsForItem(Integer idItem) {
        Item item = itemRepository.findById(idItem).get();
        return reservationRepository.findReservationsByItemId(item);
    }

    @Override
    public List<Reservation> findReservationsForUser(Integer idUser, String type) {
        User loggedUser = userRepository.findById(idUser).get();
        return this.reservationRepository.findReservationsByReserverUserAndType(loggedUser, type);
    }
}
