package com.thesis.cpmcore.service;

import com.thesis.cpmcore.model.User;

import javax.servlet.http.HttpServletRequest;

public interface UserUpdateService {

    Boolean comparePasswords(String passTyped, HttpServletRequest request);
    Boolean changePassword(User user, HttpServletRequest request);

}
