package com.thesis.cpmcore.controller;

import com.thesis.cpmcore.model.Action;
import com.thesis.cpmcore.model.Report;
import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.ReportRepository;
import com.thesis.cpmcore.repository.UserRepository;
import com.thesis.cpmcore.service.ProfileUpdateService;
import com.thesis.cpmcore.service.impl.ProfileUpdateServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class ReportController {

    private ReportRepository reportRepository;
    private ProfileUpdateService profileUpdateService;
    private UserRepository userRepository;

    public ReportController(ReportRepository reportRepository,
                            ProfileUpdateServiceImpl profileUpdateService,
                            UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.profileUpdateService = profileUpdateService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/report/new", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity addNewReport(@RequestBody Report report){
        try{
            report.setStatus(Report.CREATED);
            report.setEventDate(Timestamp.valueOf(LocalDateTime.now()));
            reportRepository.save(report);
            return ResponseEntity.status(HttpStatus.OK).body("Accepted");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }


    @RequestMapping(value = "/user/reports", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getUserReports(HttpServletRequest request){
        try{
            String username = this.profileUpdateService.getUsername(request);
            System.out.println("Username: "+ username);
            User user = this.userRepository.findByUsername(username);
            System.out.println("User: "+ user);
            List<Report> reports = this.reportRepository.findAllByDeclarant(user);
            System.out.println("Ilosc znalezionych: "+ reports.size());
            return ResponseEntity.status(HttpStatus.OK).body(reports);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }

}
