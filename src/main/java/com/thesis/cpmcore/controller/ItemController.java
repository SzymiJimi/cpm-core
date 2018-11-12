package com.thesis.cpmcore.controller;


import com.thesis.cpmcore.model.Item;
import com.thesis.cpmcore.repository.ItemRepository;
import com.thesis.cpmcore.service.AvailabilityChecker;
import com.thesis.cpmcore.service.impl.AvailabilityCheckerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ItemController {

    private ItemRepository itemRepository;
    private AvailabilityChecker availabilityChecker;

    @Autowired
    public ItemController(ItemRepository itemRepository, AvailabilityCheckerImpl availabilityChecker){
        this.itemRepository = itemRepository;
        this.availabilityChecker = availabilityChecker;
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getItemList(){
        try{
            List<Item> items = this.itemRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(items);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getItemById(@PathVariable(value = "id")Integer id){
        try{
            Item item = this.itemRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(item);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }


    @RequestMapping(value = "/item/new", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity addNewItem(@RequestBody Item item){
        try{
            itemRepository.save(item);
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }

    @RequestMapping(value = "/item/availability/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity availabilityOfItem(@PathVariable(value = "id")Integer id){
        try{
            if(availabilityChecker.checkAvailabilityOfItem(id)){
                return ResponseEntity.status(HttpStatus.OK).body("Available");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not available");
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error with checking availability");
        }
    }


}
