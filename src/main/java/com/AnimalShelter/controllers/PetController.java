package com.AnimalShelter.controllers;


import com.AnimalShelter.models.Pet;
import com.AnimalShelter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/{idPet}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long idPet) {
        try {
            Pet pet = petService.findPetById(idPet);
            return new ResponseEntity<>(pet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idPet}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long idPet, @RequestBody Pet pet) {
        pet.setIdPet(idPet);
        Pet updatedPet = petService.updatePet(pet);
        return new ResponseEntity<>(updatedPet, HttpStatus.OK);
    }

    @PostMapping("/{idPet}/adopt/{idUser}")
    public ResponseEntity<Void> adoptPet(@PathVariable Long idPet, @PathVariable Long idUser) {
        try {
            petService.adoptPet(idPet, idUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
