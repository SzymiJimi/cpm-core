package com.thesis.cpmcore.service.impl;

import com.thesis.cpmcore.model.Role;
import com.thesis.cpmcore.model.User;
import com.thesis.cpmcore.repository.UserRepository;
import com.thesis.cpmcore.service.ProfileUpdateService;
import com.thesis.cpmcore.service.ReportService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ReportServiceImpl implements ReportService {

    private ProfileUpdateService profileUpdateService;
    private UserRepository userRepository;

    public ReportServiceImpl(ProfileUpdateService profileUpdateService,
                             UserRepository userRepository) {
        this.profileUpdateService = profileUpdateService;
        this.userRepository = userRepository;

    }

    @Override
    public User getAndCheckUser(HttpServletRequest request) {
        String username =  profileUpdateService.getUsername(request);
        User user = this.userRepository.findByUsername(username);
        return chechPermissionForReportChange(user) ? user : null;
    }

    private boolean chechPermissionForReportChange(User user){
        return user.getIdRole().getName().equals(Role.MANAGER);
    }
}
