package com.AnimalShelter.services;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.models.User;
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
//    public List<Donation> getTopDonor() {
//            return iDonationRepository.findAll();
//
//    }

//    public ArrayList<Map<User, Integer>> getTopDonor(User user){
//
//        //usuario, cantidad de donaciones total
//
//
//        ArrayList<map> list = new ArrayList<>(map.value);
//
//        list.stream().sorted();}


    }




