package com.thesis.cpmcore.service;

import com.thesis.cpmcore.model.User;

import javax.servlet.http.HttpServletRequest;

public interface ProfileUpdateService {

    Boolean compareProfiles(User user, HttpServletRequest request);
    void updateProfileData(User user);
    Boolean checkPermission(HttpServletRequest request);
    void updateRole(User user);
    String getUsername(HttpServletRequest request);

}
