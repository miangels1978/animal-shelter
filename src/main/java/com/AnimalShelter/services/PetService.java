package com.AnimalShelter.services;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.repositories.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    IPetRepository iPetRepository;


    public Pet addNewPet(Pet pet) {
        return iPetRepository.save(pet);
    }

    public List<Pet> getAllAvailablePets() {
        return iPetRepository.findByIsadoptedFalse();
    }

    public List<Pet> getAllAdoptedPets() {
        return iPetRepository.findByIsadoptedTrue();
    }

    public List<Pet> getAllPets() {
        try {
            return (List<Pet>) iPetRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all pets.", e);
        }
    }

    public Optional<Pet> getPetById(long id) {
        try {
            return iPetRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving pet by id.", e);
        }
    }

    public void deletePetById(Long id) {
        iPetRepository.deleteById(id);
    }

    public void deleteAllPets() {
        iPetRepository.deleteAll();
    }

}
