package com.AnimalShelter.services;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.models.User;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PetService {
    @Autowired
    IPetRepository iPetRepository;

    @Autowired
    IUserRepository iUserRepository;
    private UserService userService;

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
        existingPet.setIsAdopted(pet.getIsAdopted());
        existingPet.setUrl(pet.getUrl());
        return iPetRepository.save(existingPet);
    }
    public void adoptPet(Long idPet, Long idUser) {
        Pet pet = findPetById(idPet);
       
        User user = userService.findUserById(idUser);
        pet.setUser(user);
        iPetRepository.save(pet);
    }
}
