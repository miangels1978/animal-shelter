package com.AnimalShelter.controllers;

import com.AnimalShelter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pet")
@CrossOrigin(origins = "*")

public class PetController {
    @Autowired
    PetService petService;
}
