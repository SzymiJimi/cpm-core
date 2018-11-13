package com.thesis.cpmcore.service.impl;

import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.UserRepository;
import com.thesis.cpmcore.service.ProfileUpdateService;
import com.thesis.cpmcore.service.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserUpdateServiceImpl implements UserUpdateService {

    private UserRepository userRepository;
    private ProfileUpdateService profileUpdateService;

    @Autowired
    public UserUpdateServiceImpl(UserRepository userRepository,
                                 ProfileUpdateServiceImpl profileUpdateService){
        this.userRepository = userRepository;
        this.profileUpdateService = profileUpdateService;
    }

    @Override
    public Boolean comparePasswords(String passTyped, HttpServletRequest request) {
        String username = this.profileUpdateService.getUsername(request);
        User foundUser  = userRepository.findByUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(bCryptPasswordEncoder.encode(passTyped), foundUser.getPassword());
    }

    @Override
    public Boolean changePassword(User user, HttpServletRequest request) {
        return this.profileUpdateService.compareProfiles(user, request) && this.changePassword(user);
    }


    private Boolean changePassword(User user){
        this.userRepository.save(user);
        return true;
    }
}
