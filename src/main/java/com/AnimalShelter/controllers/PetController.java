package com.AnimalShelter.controllers;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
@CrossOrigin(origins = "*")

public class PetController {
    @Autowired
    PetService petService;

    @PostMapping (path = "/newPets")
    public Pet addNewPet(@RequestBody Pet pet) {
        return petService.addNewPet(pet);
    }

    @GetMapping(path = "/availablePets")
    public List<Pet> getAllAvailablePets() {
        return petService.getAllAvailablePets();
    }

    @GetMapping(path = "/adoptedPets")
    public List<Pet> getAllAdoptedPets() {
        return petService.getAllAdoptedPets();
    }

    @GetMapping(path = "")
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping(path = "/pet/{id}")
    public Optional<Pet> getPetById(@PathVariable long id) {
        return petService.getPetById(id);
    }

    @DeleteMapping(path = "pet/{id}")
    public ResponseEntity<String> deletePetById(@PathVariable Long id) {
        Optional<Pet> pet = petService.getPetById(id);
        if (pet.isPresent()) {
            petService.deletePetById(id);
            return new ResponseEntity<>("Pet deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Pet with id " + id + " was not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public void deleteAllPets() {
        petService.deleteAllPets();
    }
}
