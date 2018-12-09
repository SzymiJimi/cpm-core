package com.thesis.cpmcore.service.impl;

import com.thesis.cpmcore.model.Action;
import com.thesis.cpmcore.model.Item;
import com.thesis.cpmcore.repository.ActionRepository;
import com.thesis.cpmcore.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private ActionRepository actionRepository;

    public ItemServiceImpl(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Override
    public List<Item> checkItemsLocation(List<Item> items) {
        return  items
                .stream()
                .map(item -> item= changeItemLocation(item))
                .collect(Collectors.toList());
    }

    private Item changeItemLocation(Item item){
        Date currentTime = new Date();
        List<Action> actions = this.actionRepository.findReservationsByItemId(item);
        actions.forEach(
                action -> {
                    if(action.getFrom().before(currentTime) && action.getTo().after(currentTime))
                    {
                        item.setLocation(action.getLocation());
                    }
                }
        );
        return item;
    }
}
