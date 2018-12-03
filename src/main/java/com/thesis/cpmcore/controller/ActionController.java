package com.thesis.cpmcore.controller;

import com.thesis.cpmcore.model.Action;
import com.thesis.cpmcore.repository.ActionRepository;
import com.thesis.cpmcore.repository.UserRepository;
import com.thesis.cpmcore.service.ActionService;
import com.thesis.cpmcore.service.impl.ActionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ActionController {

    private ActionRepository actionRepository;
    private UserRepository userRepository;
//    private ItemRepository itemRepository;
    private ActionService actionService;

    @Autowired
    public ActionController(ActionRepository actionRepository,
                            UserRepository userRepository,
                            ActionServiceImpl reservationService){
        this.actionRepository = actionRepository;
        this.userRepository = userRepository;
        this.actionService = reservationService;
    }

    @RequestMapping(value = "/reservations/user/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getItemList(@PathVariable(value = "id") Integer idUser){
        try{
            List<Action> reservations = this.actionService.findReservationsForUser(idUser, Action.RESERVATION);
            return ResponseEntity.status(HttpStatus.OK).body(reservations);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }

    @RequestMapping(value = "/reservation/new", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity addNewReservation(@RequestBody Action reservation){
        try{
            reservation.setType(Action.RESERVATION);
            actionRepository.save(reservation);
            return ResponseEntity.status(HttpStatus.OK).body("Accepted");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }

    @RequestMapping(value = "/reservations/item/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity findReservationsForItem(@PathVariable(value = "id") Integer idItem){
        try{
             List<Action> reservations = this.actionService.findReservationsForItem(idItem, Action.RESERVATION);
            return ResponseEntity.status(HttpStatus.OK).body(reservations);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }

    @RequestMapping(value = "/reservations/all/item/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity findAllReservationsForItem(@PathVariable(value = "id") Integer idItem){
        try{
            List<Action> reservations = this.actionService.findAllReservationsForItem(idItem);
            return ResponseEntity.status(HttpStatus.OK).body(reservations);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }

    @RequestMapping(value = "/action/get/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getSingleReservation(@PathVariable(value = "id") Integer idReservation){
        try{
            Action reservation = this.actionRepository.findReservationByIdReservation(idReservation);
            return ResponseEntity.status(HttpStatus.OK).body(reservation);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }


    @RequestMapping(value = "/action/finish", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity finishAction(@RequestBody Action action){
        try{
            action.setTo(new Timestamp(Calendar.getInstance().getTimeInMillis()+3600000));
            if(action.getTo().before(action.getFrom())){
                action.setFrom(new Timestamp(Calendar.getInstance().getTimeInMillis()+3600000));
            }
            Action newAction = this.actionRepository.save(action);
            return ResponseEntity.status(HttpStatus.OK).body(newAction);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }


    @RequestMapping(value = "/actions/{period}/user/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getActionsForUser(@PathVariable(value = "id") Integer idUser, @PathVariable(value = "period") String period){
        try{
            List<Action> reservations;
            if(period.equals("before")){
                reservations = this.actionRepository
                        .findReservationsByReserverUserIdUser(idUser)
                        .stream()
                        .filter(entry -> entry.getTo().before(new Timestamp(Calendar.getInstance().getTimeInMillis()+3600000)))
                        .collect(Collectors.toList());
            }else{
                reservations = this.actionRepository
                        .findReservationsByReserverUserIdUser(idUser)
                        .stream()
                        .filter(entry -> entry.getTo().after(new Timestamp(Calendar.getInstance().getTimeInMillis()+3600000)))
                        .collect(Collectors.toList());
            }
            return ResponseEntity.status(HttpStatus.OK).body(reservations);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }
}
