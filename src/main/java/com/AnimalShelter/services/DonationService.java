package com.AnimalShelter.services;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.repositories.IDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    IDonationRepository iDonationRepository;

    public Optional<Donation> findById(Long id){
        return iDonationRepository.findById(id);
    }
    public void deleteDonationById(Long id){
        iDonationRepository.deleteById(id);
    }
    public void deleteAllDonations(){
        iDonationRepository.deleteAll();
    }
}
