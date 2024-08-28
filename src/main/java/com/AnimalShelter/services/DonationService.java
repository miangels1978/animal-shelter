package com.AnimalShelter.services;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.repositories.IDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationService {

    @Autowired
    IDonationRepository iDonationRepository;

    public Donation createDonation(Donation donation) {
        return iDonationRepository.save(donation);
    }

}
