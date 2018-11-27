package com.thesis.cpmcore.controller;

import com.thesis.cpmcore.model.Location;
import com.thesis.cpmcore.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@CrossOrigin
public class LocationController {

    private LocationRepository locationRepository;

    @Autowired
    public LocationController(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getItemList(){
        try{
            List<Location> locations = this.locationRepository.findAll();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(locations);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }

    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getItemList(@PathVariable(value="id") Integer id){
        try{
            Location location = this.locationRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(location);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching location from db");
        }

    }


    @RequestMapping(value = "/location/update", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity updateLocation(@RequestBody Location location){
        try{
            this.locationRepository.save(location);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching location from db");
        }

    }


    @RequestMapping(value = "/location/new", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity addNewLocation(@RequestBody Location newLocation){
        try{
            this.locationRepository.save(newLocation);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching location from db");
        }

    }


    @RequestMapping(value = "/location/delete", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity deleteLocation(@RequestBody Location location){
        try{
            this.locationRepository.delete(location);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching location from db");
        }

    }

}
