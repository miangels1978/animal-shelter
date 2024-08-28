package com.AnimalShelter.controllers;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
@CrossOrigin(origins = "*")

public class PetController {
    @Autowired
    PetService petService;

    @PostMapping(path = "/pets")
    public Pet addNewPet(@RequestBody Pet pet){ return petService.addNewPet(pet);    }

}
