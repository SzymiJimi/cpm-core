package com.thesis.cpmcore.service;

import com.thesis.cpmcore.model.User;

import javax.servlet.http.HttpServletRequest;

public interface ReportService {

    User getAndCheckUser(HttpServletRequest request);

}
