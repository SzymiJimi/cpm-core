package com.thesis.cpmcore.service.impl;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.thesis.cpmcore.model.Item;
import com.thesis.cpmcore.model.Reservation;
import com.thesis.cpmcore.repository.ItemRepository;
import com.thesis.cpmcore.repository.ReservationRepository;
import com.thesis.cpmcore.service.AvailabilityChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Service
public class AvailabilityCheckerImpl implements AvailabilityChecker {

    private ItemRepository itemRepository;
    private ReservationRepository reservationRepository;
    private List<Reservation> reservationsForItem;

    @Autowired
    public AvailabilityCheckerImpl(ItemRepository itemRepository, ReservationRepository reservationRepository){
        this.itemRepository = itemRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Boolean checkAvailabilityOfItem(@NotNull Integer id) {
        try {
            Item item = getItemFromDb(id);
            loadReservationsForItem(item);
            if(checkAvailability()){
                System.out.println("Dostepny");
                return true;
            }else{
                System.out.println("Niedostepny");
                return false;
            }

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    private Item getItemFromDb(@NotNull Integer id){
            return this.itemRepository.findById(id).get();
    }

    private void loadReservationsForItem(@NotNull Item item){
        this.reservationsForItem = this.reservationRepository.findReservationsByItemId(item);
    }

    private Boolean checkAvailability(){
        Date currentTime = new Date();
        Boolean available = true;
        for (Reservation reservation: reservationsForItem) {
//            System.out.println("Dzisiejszy dzien:" + currentTime.toString());
//            System.out.println("From: " + reservation.getFrom().toString());
//            System.out.println("To: " + reservation.getTo().toString());
//            System.out.println("Czy from jest przed: " + reservation.getFrom().before(currentTime) );
//            System.out.println("Czy to jest za: " + reservation.getTo().after(currentTime) );
            if(reservation.getFrom().before(currentTime)&& reservation.getTo().after(currentTime)){
                available = false;
            }
        }
        return available;
    }

}