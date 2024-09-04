package com.AnimalShelter.controllers;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/donations")
@CrossOrigin(origins = "*")
public class DonationController {
    @Autowired
    DonationService donationService;

    @PostMapping
    public Donation createDonation(@RequestBody Donation donation) {
        return donationService.createDonation(donation);
    }
}
