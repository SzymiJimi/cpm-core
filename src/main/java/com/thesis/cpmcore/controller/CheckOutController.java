package com.thesis.cpmcore.controller;

import com.thesis.cpmcore.model.Action;
import com.thesis.cpmcore.repository.ItemRepository;
import com.thesis.cpmcore.repository.ActionRepository;
import com.thesis.cpmcore.repository.UserRepository;
import com.thesis.cpmcore.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CheckOutController {

    private ActionRepository actionRepository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private ActionService actionService;

    @Autowired
    public CheckOutController(ActionRepository actionRepository,
                              UserRepository userRepository,
                              ItemRepository itemRepository,
                              ActionService actionService){
        this.actionRepository = actionRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.actionService = actionService;
    }


    @RequestMapping(value = "/checkout/new", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity addNewCheckOut(@RequestBody Action reservation){
        try{
            reservation.setType(Action.CHECK_OUT);
            actionRepository.save(reservation);
            return ResponseEntity.status(HttpStatus.OK).body("Accepted");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }

    @RequestMapping(value = "/checkout/item/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity findCheckOutsForItem(@PathVariable(value = "id") Integer idItem){
        try{
            System.out.println("Wchodzi prośba");
            List<Action> reservations = this.actionService.findReservationsForItem(idItem, Action.CHECK_OUT);
            return ResponseEntity.status(HttpStatus.OK).body(reservations);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }

    @RequestMapping(value = "/checkout/user/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getItemList(@PathVariable(value = "id") Integer idUser){
        try{
            List<Action> reservations = this.actionService.findReservationsForUser(idUser, Action.CHECK_OUT);
            return ResponseEntity.status(HttpStatus.OK).body(reservations);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }

}
