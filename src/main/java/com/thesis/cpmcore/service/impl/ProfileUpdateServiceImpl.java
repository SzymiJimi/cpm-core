package com.thesis.cpmcore.service.impl;

import com.thesis.cpmcore.model.Personaldata;
import com.thesis.cpmcore.model.Role;
import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.PersondataRepository;
import com.thesis.cpmcore.repository.RoleRepository;
import com.thesis.cpmcore.repository.UserRepository;
import com.thesis.cpmcore.service.ProfileUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@Service
public class ProfileUpdateServiceImpl implements ProfileUpdateService {


    private PersondataRepository persondataRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public ProfileUpdateServiceImpl(PersondataRepository persondataRepository,
                                    UserRepository userRepository,
                                    RoleRepository roleRepository){
        this.persondataRepository = persondataRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public Boolean compareProfiles(User user, HttpServletRequest request) {
        String username = this.getUsername(request);
        User userFomAuth = this.getUserFromDb(username);
        return this.compareUsers(user, userFomAuth);
    }

    @Override
    public void updateProfileData(User user) {
        Personaldata personaldata = user.getIdPersonaldata();
        this.persondataRepository.save(personaldata);
    }

    @Override
    public Boolean checkPermission(HttpServletRequest request) {
        String username = this.getUsername(request);
        User user = getUserFromDb(username);
        return this.checkManagerRights(user);
    }

    @Override
    public void updateRole(User user) {
        Role role = this.roleRepository.findByName(user.getIdRole().getName());
        System.out.println("Znaleziona rola: " + role.toString());
        user.setIdRole(role);
        this.userRepository.save(user);
    }

    @Override
    public String getUsername(HttpServletRequest request){
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        Principal principal = () -> new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
        return  principal.getName();
    }

    private User getUserFromDb(String username){
       return this.userRepository.findByUsername(username);
    }

    private Boolean compareUsers(User fromBody, User userFromAuth){
        return fromBody.getUsername().equals(userFromAuth.getUsername()) &&
                fromBody.getEmail().equals(userFromAuth.getEmail());
    }

    private Boolean checkManagerRights(User user){
        return user.getIdRole().getName().equals(Role.MANAGER);
    }

}
