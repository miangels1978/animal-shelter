package com.AnimalShelter.controllers;

import com.AnimalShelter.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/donations")
@CrossOrigin(origins = "*")
public class DonationController {
    @Autowired
    DonationService donationService;
}
