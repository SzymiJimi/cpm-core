package com.thesis.cpmcore.controller;

import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    private UserRepository userRepository;

    public LoginController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @RequestMapping("/login")
    @CrossOrigin(origins ={"http://192.168.0.54:4200", "http://localhost:4200"}, allowCredentials = "true")
    public boolean login(@RequestBody User user) {
        User foundUser  = userRepository.findByUsername(user.getUsername());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword());
    }

}
