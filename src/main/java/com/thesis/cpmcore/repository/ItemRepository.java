package com.thesis.cpmcore.repository;

import com.thesis.cpmcore.model.Item;
import com.thesis.cpmcore.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAllByLocation(Location location);
    List<Item> findAllByBrand(String brand);
    void deleteAllByBrand(String brand);
}
