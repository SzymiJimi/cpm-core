package com.thesis.cpmcore.controller;

import com.thesis.cpmcore.model.Sheet;
import com.thesis.cpmcore.model.Stocktaking;
import com.thesis.cpmcore.repository.SheetRepository;
import com.thesis.cpmcore.repository.StocktakingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SheetController {

    private SheetRepository sheetRepository;
    private StocktakingRepository stocktakingRepository;

    public SheetController(SheetRepository sheetRepository,
                           StocktakingRepository stocktakingRepository) {
        this.sheetRepository = sheetRepository;
        this.stocktakingRepository = stocktakingRepository;
    }

    @RequestMapping(value = "/stocktaking/load/sheets/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getAllSheetsForStocktaking(@PathVariable(value="id") Integer idStocktaking){
        try{
            Stocktaking stocktaking = stocktakingRepository.findById(idStocktaking).orElse(new Stocktaking());
            List<Sheet> stocktakings = this.sheetRepository.findByIdStocktaking(stocktaking);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(stocktakings);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching last date");
        }
    }

}
