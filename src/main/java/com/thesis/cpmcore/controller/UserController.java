package com.thesis.cpmcore.controller;


import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@CrossOrigin
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping("/user")
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
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

}
