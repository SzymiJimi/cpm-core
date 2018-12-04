package com.thesis.cpmcore.repository;

import com.thesis.cpmcore.model.Stocktaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StocktakingRepository extends JpaRepository<Stocktaking, Integer> {

    List<Stocktaking> findByLocationIdLocation(Integer idLocation);
    List<Stocktaking> findByManagerIdUser(Integer idUser);

}
