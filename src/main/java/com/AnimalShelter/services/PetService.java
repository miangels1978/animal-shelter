package com.AnimalShelter.services;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.repositories.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    @Autowired
    IPetRepository iPetRepository;

    public Pet addNewPet(Pet pet) { return iPetRepository.save(pet);}

}
