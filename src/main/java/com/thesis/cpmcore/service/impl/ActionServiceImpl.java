package com.thesis.cpmcore.service.impl;

import com.thesis.cpmcore.model.Item;
import com.thesis.cpmcore.model.Action;
import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.ItemRepository;
import com.thesis.cpmcore.repository.ActionRepository;
import com.thesis.cpmcore.repository.UserRepository;
import com.thesis.cpmcore.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {


    private ActionRepository actionRepository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;

    @Autowired
    public ActionServiceImpl(ActionRepository actionRepository,
                             UserRepository userRepository,
                             ItemRepository itemRepository){
        this.actionRepository = actionRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Action> findReservationsForItem(Integer idItem, String type) {
        Item item = itemRepository.findById(idItem).get();
        return actionRepository.findReservationsByItemIdAndType(item, type);
    }

    @Override
    public List<Action> findAllReservationsForItem(Integer idItem) {
        Item item = itemRepository.findById(idItem).get();
        return actionRepository.findReservationsByItemId(item);
    }

    @Override
    public List<Action> findReservationsForUser(Integer idUser, String type) {
        User loggedUser = userRepository.findById(idUser).get();
        return this.actionRepository.findReservationsByReserverUserAndType(loggedUser, type);
    }
}
