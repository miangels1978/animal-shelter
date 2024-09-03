package com.AnimalShelter.controllers;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
@CrossOrigin(origins = "*")

public class PetController {
    @Autowired
    PetService petService;

    @GetMapping("/{idPet}")
    public ResponseEntity<Pet> findPetById(@PathVariable Long idPet) {
        Pet pet = petService.findPetById(idPet);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @PutMapping("/{idPet}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long idPet, @RequestBody Pet pet) {
        pet.setIdPet(idPet);
        Pet updatedPet = petService.updatePet(pet);
        return new ResponseEntity<>(updatedPet, HttpStatus.OK);
    }

    @PostMapping("/{idPet}/adopt")
    public ResponseEntity<Void> adoptPet(@PathVariable Long idPet, @RequestParam Long idUser) {
        petService.adoptPet(idPet, idUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
