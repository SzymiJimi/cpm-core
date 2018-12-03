package com.thesis.cpmcore.controller;

import com.thesis.cpmcore.model.Action;
import com.thesis.cpmcore.model.Report;
import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.ReportRepository;
import com.thesis.cpmcore.repository.UserRepository;
import com.thesis.cpmcore.service.ProfileUpdateService;
import com.thesis.cpmcore.service.ReportService;
import com.thesis.cpmcore.service.impl.ProfileUpdateServiceImpl;
import com.thesis.cpmcore.service.impl.ReportServiceImpl;
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
    private ReportService reportService;

    public ReportController(ReportRepository reportRepository,
                            ProfileUpdateServiceImpl profileUpdateService,
                            UserRepository userRepository,
                            ReportServiceImpl reportService) {
        this.reportRepository = reportRepository;
        this.profileUpdateService = profileUpdateService;
        this.userRepository = userRepository;
        this.reportService = reportService;
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
            User user = this.userRepository.findByUsername(username);
            List<Report> reports = this.reportRepository.findAllByDeclarant(user);
            return ResponseEntity.status(HttpStatus.OK).body(reports);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }


    @RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getUserReports(@PathVariable(value = "id") Integer id,  HttpServletRequest request){
        try{
            System.out.println("Otrzymane id: " + id);
            Report report = this.reportRepository.findByIdRequest(id);
            System.out.println("Report: " + report.toString());
            return ResponseEntity.status(HttpStatus.OK).body(report);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }

    @RequestMapping(value = "/report/consider/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity startConsideration(@PathVariable(value = "id") Integer id,  HttpServletRequest request){
        try{
            User repairMan = reportService.getAndCheckUser(request);
            Report report = this.reportRepository.findByIdRequest(id);
            if(repairMan!=null && report!=null){
                report.setStatus(Report.REPAIRING);
                report.setServiceman(repairMan);
                this.reportRepository.save(report);
                return ResponseEntity.status(HttpStatus.OK).body(report);
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You don't have permission");
            }

        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }


    @RequestMapping(value = "/report/finish", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity finishReport(@RequestBody Report report){
        try{
            report.setStatus(Report.FINISHED);
            this.reportRepository.save(report);
            return ResponseEntity.status(HttpStatus.OK).body("Success!");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with finishing report!");
        }
    }

}
