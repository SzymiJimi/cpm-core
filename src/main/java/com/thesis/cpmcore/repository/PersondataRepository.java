package com.thesis.cpmcore.repository;

import com.thesis.cpmcore.model.Personaldata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersondataRepository extends JpaRepository<Personaldata, Integer> {
}
