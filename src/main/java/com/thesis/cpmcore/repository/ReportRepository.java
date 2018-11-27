package com.thesis.cpmcore.repository;

import com.thesis.cpmcore.model.Report;
import com.thesis.cpmcore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    List<Report> findAllByDeclarant(User declarant);

}
