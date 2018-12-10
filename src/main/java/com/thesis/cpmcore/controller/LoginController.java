package com.thesis.cpmcore.controller;

import com.thesis.cpmcore.model.Personaldata;
import com.thesis.cpmcore.model.Role;
import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.PersondataRepository;
import com.thesis.cpmcore.repository.RoleRepository;
import com.thesis.cpmcore.repository.UserRepository;
import com.thesis.cpmcore.service.RegisterService;
import com.thesis.cpmcore.service.impl.RegisterServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    private UserRepository userRepository;
    private RegisterService registerService;
    private PersondataRepository persondataRepository;
    private RoleRepository roleRepository;

    public LoginController(UserRepository userRepository,
                           RegisterServiceImpl registerService,
                           PersondataRepository persondataRepository,
                           RoleRepository roleRepository)
    {
        this.userRepository = userRepository;
        this.registerService = registerService;
        this.persondataRepository = persondataRepository;
        this.roleRepository = roleRepository;
    }

    @RequestMapping("/login")
    @CrossOrigin(origins ={"http://192.168.0.54:4200", "http://localhost:4200"}, allowCredentials = "true")
    public boolean login(@RequestBody User user) {
        User foundUser  = userRepository.findByUsername(user.getUsername());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword());
    }

    @RequestMapping(value = "/register/new", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity addNewReport(@RequestBody User newUser){
        try{
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if(this.registerService.checkCreationData(newUser)){
                newUser.setIdPersonaldata(this.persondataRepository.save(newUser.getIdPersonaldata()));
                newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
                newUser.setIdRole(this.roleRepository.findByName(Role.USER));
                this.userRepository.save(newUser);
                return ResponseEntity.status(HttpStatus.OK).body("Accepted");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with email or username exists");
            }

        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating account");
        }
    }



}
