package com.AnimalShelter.services;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.repositories.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {
    @Autowired
    IPetRepository iPetRepository;


    public void deletePetById(Long id){
        iPetRepository.deleteById(id);
    }

    public void deleteAllPets(){
        iPetRepository.deleteAll();
    }

    public Optional<Pet> findById(Long id){
        return iPetRepository.findById(id);
    }
}
