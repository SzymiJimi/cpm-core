package com.thesis.cpmcore.controller;


import com.thesis.cpmcore.model.Item;
import com.thesis.cpmcore.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ItemController {

    ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getItemList(){
        try{
            List<Item> items = this.itemRepository.findAll();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(items);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }

    }

}
