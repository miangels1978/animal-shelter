package com.AnimalShelter.controllers;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/donations")
@CrossOrigin(origins = "*")
public class DonationController {
    @Autowired
    DonationService donationService;


    @DeleteMapping(path = "/donation/{id}")
    public ResponseEntity<String> deleteDonationById(@PathVariable Long id) {
        Optional<Donation> donation = donationService.findById(id);
        if (donation.isPresent()) {
            donationService.deleteDonationById(id);
            return new ResponseEntity<>("Donation deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with id "+id+" was not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public void deleteAllDonations() {
        donationService.deleteAllDonations();
    }
}
