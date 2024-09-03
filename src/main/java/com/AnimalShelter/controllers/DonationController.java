package com.AnimalShelter.controllers;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.models.User;
import com.AnimalShelter.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/donations")
@CrossOrigin(origins = "*")

public class DonationController {
    @Autowired
    DonationService donationService;

    @GetMapping
    public List<Donation> getAllDonation(){
        return donationService.getAllDonation();
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Donation> getDonationById(@PathVariable long id){
        return donationService.getDonationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @GetMapping(path = "")
//    public List<Donation> getTopDonor(){
//        return donationService.getTopDonor();
//    }
//    @GetMapping(path = "")
//    public List<User> getTotalAmountDonatedByUser(){
//        return donationService.getTotalAmountDonatedByUser();
//    }
//
//    @GetMapping("")
//    public List<User> getUserWithMostDonations() {
//        return donationService.getUserWithMostDonations();
//    }

}
