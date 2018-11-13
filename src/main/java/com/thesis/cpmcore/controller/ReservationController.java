package com.thesis.cpmcore.controller;

import com.thesis.cpmcore.model.Item;
import com.thesis.cpmcore.model.Reservation;
import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.ItemRepository;
import com.thesis.cpmcore.repository.ReservationRepository;
import com.thesis.cpmcore.repository.UserRepository;
import com.thesis.cpmcore.service.ReservationService;
import com.thesis.cpmcore.service.impl.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ReservationController {

    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
//    private ItemRepository itemRepository;
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository,
                                 UserRepository userRepository,
                                 ReservationServiceImpl reservationService){
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.reservationService = reservationService;
    }

    @RequestMapping(value = "/reservations/user/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getItemList(@PathVariable(value = "id") Integer idUser){
        try{
            List<Reservation> reservations = this.reservationService.findReservationsForUser(idUser, Reservation.RESERVATION);
            return ResponseEntity.status(HttpStatus.OK).body(reservations);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }

    @RequestMapping(value = "/reservation/new", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity addNewReservation(@RequestBody Reservation reservation){
        try{
            reservation.setType(Reservation.RESERVATION);
            reservationRepository.save(reservation);
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
             List<Reservation> reservations = this.reservationService.findReservationsForItem(idItem, Reservation.RESERVATION);
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
            List<Reservation> reservations = this.reservationService.findAllReservationsForItem(idItem);
            return ResponseEntity.status(HttpStatus.OK).body(reservations);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }
}