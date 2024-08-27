package com.AnimalShelter.services;

import com.AnimalShelter.repositories.IDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationService {

    @Autowired
    IDonationRepository iDonationRepository;

    public void deleteDonationById(Long id){
        iDonationRepository.deleteById(id);
    }
    public void deleteAllDonations(){
        iDonationRepository.deleteAll();
    }
}
