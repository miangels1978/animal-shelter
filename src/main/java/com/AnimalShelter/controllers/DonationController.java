package com.AnimalShelter.controllers;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donation")
@CrossOrigin(origins = "*")
public class DonationController {
    @Autowired
    DonationService donationService;

    @PostMapping(path = "/donations")
    public Donation createDonation(@RequestBody Donation donation) {
        return donationService.createDonation(donation);
    }
}
