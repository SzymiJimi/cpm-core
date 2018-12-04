package com.thesis.cpmcore.service.impl;

import com.thesis.cpmcore.model.Item;
import com.thesis.cpmcore.model.Location;
import com.thesis.cpmcore.model.Sheet;
import com.thesis.cpmcore.model.Stocktaking;
import com.thesis.cpmcore.repository.ItemRepository;
import com.thesis.cpmcore.repository.SheetRepository;
import com.thesis.cpmcore.service.StocktakingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StocktakingServiceImpl implements StocktakingService {


    private ItemRepository itemRepository;
    private SheetRepository sheetRepository;

    public StocktakingServiceImpl(ItemRepository itemRepository, SheetRepository sheetRepository) {
        this.itemRepository = itemRepository;
        this.sheetRepository = sheetRepository;
    }

    @Override
    public void startStocktaking(Stocktaking stocktaking) {
        Location location = stocktaking.getLocation();
        List<Item> items = this.itemRepository.findAllByLocation(location);
        items.forEach(item -> this.sheetRepository.save(new Sheet(stocktaking,
                                                                    item,
                                                                    0,
                                                                    item.getIdItem(),
                                                                    "pcs.",
                                                                    1.00,
                                                                    item.getValue(),
                                                                    item.getValue())));
    }
}
