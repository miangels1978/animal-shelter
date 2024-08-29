package com.AnimalShelter.controllers;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pets")
@CrossOrigin(origins = "*")

public class PetController {
    @Autowired
    PetService petService;
    //eliminar linea 21 y sustituir linea 25 por metodo de service


    @DeleteMapping(path = "pet/{id}")
    public ResponseEntity<String> deletePetById(@PathVariable Long id){
        Optional<Pet> pet = petService.findById(id);
        if(pet.isPresent()){
            petService.deletePetById(id);
            return new ResponseEntity<>("Pet deleted successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Pet with id "+id+ " was not found.", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping
    public void deleteAllPets(){
    petService.deleteAllPets();}
}
