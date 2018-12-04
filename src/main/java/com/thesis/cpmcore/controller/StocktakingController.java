package com.thesis.cpmcore.controller;

import com.thesis.cpmcore.model.Stocktaking;
import com.thesis.cpmcore.repository.StocktakingRepository;
import com.thesis.cpmcore.service.StocktakingService;
import com.thesis.cpmcore.service.impl.StocktakingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class StocktakingController {

    private StocktakingRepository stocktakingRepository;
    private StocktakingService stocktakingService;

    public StocktakingController(StocktakingRepository stocktakingRepository,
                                 StocktakingServiceImpl stocktakingService) {
        this.stocktakingRepository = stocktakingRepository;
        this.stocktakingService = stocktakingService;
    }

    @RequestMapping(value = "/stocktaking/find/location/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity findLastStocktakingForLocation(@PathVariable(value="id") Integer id){
        try{
            List<Stocktaking> stocktakings = this.stocktakingRepository.findByLocationIdLocation(id);
            Timestamp lastdate = stocktakings
                    .stream()
                    .map(Stocktaking::getFinish)
                    .filter(Objects::nonNull)
                    .max(Timestamp::compareTo)
                    .orElse(null);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(lastdate);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching last date");
        }
    }

    @RequestMapping(value = "/stocktaking/new", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity startNewStocktaking(@RequestBody() Stocktaking stocktaking){
        try{
            System.out.println("Weszło");
            stocktaking.setStart(Timestamp.valueOf(LocalDateTime.now()));
            Stocktaking stocktakingResult =  this.stocktakingRepository.save(stocktaking);
            System.out.println("Teoretycznie zapisało: "+ stocktakingResult.toString());
            this.stocktakingService.startStocktaking(stocktakingResult);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching last date");
        }
    }

    @RequestMapping(value = "/stocktaking/location/manager/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity findStocktakingForLocation(@PathVariable(value="id") Integer id){
        try{
            List<Stocktaking> stocktakings = this.stocktakingRepository.findByManagerIdUser(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(stocktakings);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching last date");
        }
    }

    @RequestMapping(value = "/stocktaking/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getStocktaking(@PathVariable(value="id") Integer id){
        try{
            Stocktaking stocktaking = this.stocktakingRepository.findById(id).orElse(new Stocktaking());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(stocktaking);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching last date");
        }
    }


}
