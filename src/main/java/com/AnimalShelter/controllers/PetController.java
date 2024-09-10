package com.AnimalShelter.controllers;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pets")
@CrossOrigin(origins = "*")

public class PetController {
    @Autowired
    PetService petService;

    @GetMapping(path = "/availablePets")
        public List<Pet> getAllAvailablePets() {
        return petService.getAllAvailablePets();
    }
    @GetMapping(path = "/adoptedPets")
    public List<Pet> getAllAdoptedPets(){
        return petService.getAllAdoptedPets();
    }
    @GetMapping(path = "")
    public List<Pet> getAllPets(){
        return petService.getAllPets();
    }
    @GetMapping(path = "/pet/{id}")
    public Optional<Pet> getPetById(@PathVariable long id){
        return petService.getPetById(id);
    }
    public List<Pet> getAllAvailablePets() {
        return getAllPets()
                .stream()
                .filter(pet -> !pet.isAdopted())
                .collect(Collectors.toList());
    }

    public List<Pet> getAllAdoptedPets() {
        return getAllPets()
                .stream()
                .filter(pet -> !pet.isAdopted())
                .collect(Collectors.toList());
    }

}

