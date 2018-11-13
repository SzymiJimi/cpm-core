package com.thesis.cpmcore.controller;

import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.PersondataRepository;
import com.thesis.cpmcore.repository.UserRepository;
import com.thesis.cpmcore.service.ProfileUpdateService;
import com.thesis.cpmcore.service.impl.ProfileUpdateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class PersonaldataController {

    private ProfileUpdateService profileUpdateService;

    @Autowired
    public PersonaldataController(ProfileUpdateServiceImpl profileUpdateService){
        this.profileUpdateService = profileUpdateService;
    }


    @RequestMapping(value = "/personaldata/update/own", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity updateOwnProfile(@RequestBody User user, HttpServletRequest request){
        try{
            if(this.profileUpdateService.compareProfiles(user, request)){
                this.profileUpdateService.updateProfileData(user);
                return ResponseEntity.status(HttpStatus.OK).body("Success");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user changing is different from the user for whom the data is changing.");
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }

    @RequestMapping(value = "/personaldata/update/role", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity updateOtherProfile(@RequestBody User user, HttpServletRequest request){
        try{
            System.out.println("Updejtuje role");
            if(this.profileUpdateService.checkPermission(request)){
                this.profileUpdateService.updateRole(user);
                System.out.println("Poszlo ok");
                return ResponseEntity.status(HttpStatus.OK).body("Success");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You don't have permission to change other user role.");
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with updating user role");
        }
    }

    @RequestMapping(value = "/personaldata/manager/check", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity updateOtherProfile(HttpServletRequest request){
        try{
            if(this.profileUpdateService.checkPermission(request)){
                return ResponseEntity.status(HttpStatus.OK).body("Success");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user is manager");
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error probably user isn't manager.");
        }
    }

}
