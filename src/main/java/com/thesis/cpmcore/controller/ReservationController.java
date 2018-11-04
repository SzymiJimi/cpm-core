package com.thesis.cpmcore.controller;

import com.thesis.cpmcore.model.Reservation;
import com.thesis.cpmcore.model.User;
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

    @Autowired
    public ReservationController(ReservationRepository reservationRepository, UserRepository userRepository){
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/reservations/user/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getItemList(@PathVariable(value = "id") Integer idUser){
        try{
            System.out.println("Id usera: "+ idUser);
            User loggedUser = userRepository.findById(idUser).get();
            List<Reservation> reservations = this.reservationRepository.findReservationsByReserverUser(loggedUser);
            System.out.println("Ilość rezerwacji znalezionych: "+ reservations.size());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(reservations);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }
}
