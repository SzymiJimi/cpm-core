package com.thesis.cpmcore.repository;

import com.thesis.cpmcore.model.Sheet;
import com.thesis.cpmcore.model.Stocktaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SheetRepository extends JpaRepository<Sheet, Integer> {

    List<Sheet> findByIdStocktaking(Stocktaking idStocktaking);

}
