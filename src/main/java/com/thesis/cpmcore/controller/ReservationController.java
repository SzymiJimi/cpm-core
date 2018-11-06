package com.thesis.cpmcore.controller;

import com.thesis.cpmcore.model.Item;
import com.thesis.cpmcore.model.Reservation;
import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.ItemRepository;
import com.thesis.cpmcore.repository.ReservationRepository;
import com.thesis.cpmcore.repository.UserRepository;
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
    private ItemRepository itemRepository;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository,
                                 UserRepository userRepository,
                                 ItemRepository itemRepository){
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @RequestMapping(value = "/reservations/user/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getItemList(@PathVariable(value = "id") Integer idUser){
        try{
            System.out.println("Id usera: "+ idUser);
            User loggedUser = userRepository.findById(idUser).get();
            List<Reservation> reservations = this.reservationRepository.findReservationsByReserverUser(loggedUser);
            System.out.println("Ilość rezerwacji znalezionych: "+ reservations.size());
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
            Item item = itemRepository.findById(idItem).get();
            List<Reservation> reservations = reservationRepository.findReservationsByItemId(item);
            return ResponseEntity.status(HttpStatus.OK).body(reservations);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }
}
