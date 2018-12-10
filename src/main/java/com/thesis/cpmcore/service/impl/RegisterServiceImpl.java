package com.thesis.cpmcore.service.impl;

import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.UserRepository;
import com.thesis.cpmcore.service.RegisterService;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    private UserRepository userRepository;

    public RegisterServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean checkCreationData(User user) {
        if(this.userRepository.findByUsername(user.getUsername())!=null){
            return false;
        }else{
            if(this.userRepository.findByEmail(user.getEmail()) != null){
                return false;
            }else{
                return true;
            }
        }
    }
}
