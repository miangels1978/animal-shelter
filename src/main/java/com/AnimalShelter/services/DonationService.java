package com.AnimalShelter.services;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.repositories.IDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DonationService {

    @Autowired
    IDonationRepository iDonationRepository;

    public List<Donation> getAllDonation(){
        return iDonationRepository.findAll();
    }
    public Optional<Donation> getDonationById(long id){
            return iDonationRepository.findById(id);
    }

    public Donation createDonation(Donation donation) {
        return iDonationRepository.save(donation);
    }

}






