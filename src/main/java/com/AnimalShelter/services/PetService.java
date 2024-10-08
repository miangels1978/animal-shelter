package com.AnimalShelter.services;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PetService {
    @Autowired
    IPetRepository iPetRepository;

    @Autowired
    IUserRepository iUserRepository;
    UserService userService;


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



    public Pet findPetById(Long idPet) {
        return iPetRepository.findById(idPet).orElseThrow();
    }

    public Pet updatePet(Pet pet) {
        Pet existingPet = findPetById(pet.getIdPet());
        existingPet.setName(pet.getName());
        existingPet.setSpecies(pet.getSpecies());
        existingPet.setAge(pet.getAge());
        existingPet.setGender(pet.getGender());
        existingPet.setDescription(pet.getDescription());
        existingPet.setIsadopted(pet.getIsadopted());
        existingPet.setUrl(pet.getUrl());
        return iPetRepository.save(existingPet);
    }
    /*public void adoptPet(Long idPet, Long idUser) {
        Pet pet = findPetById(idPet);

        Optional<User> user = userService.getUserById(idUser);
        pet.setUser(user);
        iPetRepository.save(pet);
    }*/
}
