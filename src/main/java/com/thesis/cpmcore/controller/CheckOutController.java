package com.thesis.cpmcore.controller;

import com.thesis.cpmcore.model.Reservation;
import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.ItemRepository;
import com.thesis.cpmcore.repository.ReservationRepository;
import com.thesis.cpmcore.repository.UserRepository;
import com.thesis.cpmcore.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CheckOutController {

    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private ReservationService reservationService;

    @Autowired
    public CheckOutController(ReservationRepository reservationRepository,
                                 UserRepository userRepository,
                                 ItemRepository itemRepository,
                                ReservationService reservationService){
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.reservationService = reservationService;
    }


    @RequestMapping(value = "/checkout/new", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity addNewCheckOut(@RequestBody Reservation reservation){
        try{
            reservation.setType(Reservation.CHECK_OUT);
            reservationRepository.save(reservation);
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
            List<Reservation> reservations = this.reservationService.findReservationsForItem(idItem, Reservation.CHECK_OUT);
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
            List<Reservation> reservations = this.reservationService.findReservationsForUser(idUser, Reservation.CHECK_OUT);
            return ResponseEntity.status(HttpStatus.OK).body(reservations);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }

}
