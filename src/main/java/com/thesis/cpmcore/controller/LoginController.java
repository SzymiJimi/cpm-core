package com.thesis.cpmcore.controller;

import com.thesis.cpmcore.model.Credential;
import com.thesis.cpmcore.model.User;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@RestController
@CrossOrigin
public class LoginController {

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.POST)
    public String loginUser(@RequestBody Credential credential){

        System.out.println("Username: "+ credential.getUsername());
        System.out.println("Password: "+ credential.getPassword());

        return "Siema";
    }

    @RequestMapping("/user")
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public Principal user(HttpServletRequest request) {
        System.out.println("Header: "+ request.getHeader("Authorization") );
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        System.out.println("authToken: "+ authToken);
        Principal newString = () -> new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
        System.out.println("Nazwa:" + newString.getName() );

        return newString;
    }


    @RequestMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public boolean login(@RequestBody User user) {
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        return
                user.getUsername().equals("user") && user.getPassword().equals("password");
    }

    @RequestMapping("/resource")
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

}
