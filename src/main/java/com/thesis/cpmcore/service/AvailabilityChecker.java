package com.thesis.cpmcore.service;

import org.springframework.stereotype.Service;


public interface AvailabilityChecker {

    Boolean checkAvailabilityOfItem(Integer id);

}
