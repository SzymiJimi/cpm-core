package com.thesis.cpmcore.repository;

import com.thesis.cpmcore.model.Location;
import com.thesis.cpmcore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    Location findByUnitHeadIdUser(Integer idUser);

}
