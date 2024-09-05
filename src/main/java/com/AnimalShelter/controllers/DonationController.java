package com.AnimalShelter.controllers;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import java.util.List;

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

    @GetMapping
    public List<Donation> getAllDonation() {
        return donationService.getAllDonation();
    }

    @GetMapping("/donation/{id}")
    public ResponseEntity<Donation> getDonationById(@PathVariable long id) {
        return donationService.getDonationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/donation/{id}")
    public ResponseEntity<String> deleteDonationById(@PathVariable Long id) {
        Optional<Donation> donation = donationService.getDonationById(id);
        if (donation.isPresent()) {
            donationService.deleteDonationById(id);
            return new ResponseEntity<>("Donation deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with id " + id + " was not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public void deleteAllDonations() {
        donationService.deleteAllDonations();
    }
}
