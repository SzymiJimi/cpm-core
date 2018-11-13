package com.thesis.cpmcore.controller;


import com.thesis.cpmcore.model.Role;
import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.UserRepository;
import com.thesis.cpmcore.service.ProfileUpdateService;
import com.thesis.cpmcore.service.UserUpdateService;
import com.thesis.cpmcore.service.impl.UserUpdateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class UserController {

    private UserRepository userRepository;
    private UserUpdateService userUpdateService;

    @Autowired
    public UserController(UserRepository userRepository,
                          UserUpdateServiceImpl userUpdateService){
        this.userUpdateService = userUpdateService;
        this.userRepository = userRepository;
    }

    @RequestMapping("/user")
    @CrossOrigin(origins = {"http://192.168.0.54:4200", "http://localhost:4200"}, allowCredentials = "true")
    public ResponseEntity user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        Principal principal = () -> new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
        try {
            User user = userRepository.findByUsername(principal.getName());
            return ResponseEntity.status(HttpStatus.OK).body(user);

        }catch(Exception e ){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Unexpected error with fetching user data");
        }
    }

    @RequestMapping(value = "/user/changepass/compare", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity compareOldPass(@RequestBody String passTyped, HttpServletRequest request){
        try{
            if(this.userUpdateService.comparePasswords(passTyped, request)){
                return ResponseEntity.status(HttpStatus.OK).body("Success");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password not matches");
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with checking user pass");
        }
    }


    @RequestMapping(value = "/user/changepass", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity changePassword(@RequestBody User user, HttpServletRequest request){
        try{
            if(this.userUpdateService.changePassword(user, request)){
                return ResponseEntity.status(HttpStatus.OK).body("Success");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user changing is different from the user for whom the data is changing");
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with updating user role");
        }
    }

    @RequestMapping(value = "/users/manager", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getUsersListForManager( HttpServletRequest request){
        try{
            List<User> users = this.userRepository.findAll();
            List<User> usersToReturn =  users
                    .stream()
                    .filter(user1 -> !user1
                            .getIdRole()
                            .getName()
                            .equals(Role.HEAD))
                    .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.OK).body(usersToReturn);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with updating user role");
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity getItemById(@PathVariable(value = "id")Integer id){
        try{
            User user = this.userRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with fetching data from database");
        }
    }


}
