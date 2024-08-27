package com.AnimalShelter.services;

import com.AnimalShelter.repositories.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
