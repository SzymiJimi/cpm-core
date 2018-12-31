package com.thesis.cpmcore.service.impl;

import com.thesis.cpmcore.model.Item;
import com.thesis.cpmcore.model.Action;
import com.thesis.cpmcore.repository.ItemRepository;
import com.thesis.cpmcore.repository.ActionRepository;
import com.thesis.cpmcore.service.AvailabilityChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Service
public class AvailabilityCheckerImpl implements AvailabilityChecker {

    private ItemRepository itemRepository;
    private ActionRepository actionRepository;
    private List<Action> reservationsForItem;

    @Autowired
    public AvailabilityCheckerImpl(ItemRepository itemRepository, ActionRepository actionRepository){
        this.itemRepository = itemRepository;
        this.actionRepository = actionRepository;
    }

    @Override
    public Boolean checkAvailabilityOfItem(@NotNull Integer id) {
        try {
            Item item = getItemFromDb(id);
            loadReservationsForItem(item);
            if(checkAvailability()){
                return true;
            }else{
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
        this.reservationsForItem = this.actionRepository.findReservationsByItemId(item);
    }

    private Boolean checkAvailability(){
        Date currentTime = new Date();
        Boolean available = true;
        for (Action reservation: reservationsForItem) {
            if(reservation.getFrom().before(currentTime)&& reservation.getTo().after(currentTime)){
                available = false;
            }
        }
        return available;
    }

}
