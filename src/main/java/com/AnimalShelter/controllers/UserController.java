package com.AnimalShelter.controllers;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.models.User;
import com.AnimalShelter.services.PetService;
import com.AnimalShelter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;

    @GetMapping("/{idUser}")
    public ResponseEntity<User> getUserById(@PathVariable Long idUser) {
        try {
            User user = userService.findUserById(idUser);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<User> updateUser(@PathVariable Long idUser, @RequestBody User user) {
        user.setIdUser(idUser);
        User updatedUser = userService.updateUser(user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{idUser}/adopt/{idPet}")
    public ResponseEntity<Void> adoptPet(@PathVariable Long idUser, @PathVariable Long idPet) {
        try {
            // Call the adoptPet method from the petService with the provided IDs
            petService.adoptPet(idPet, idUser);

            // Update the pet status if needed, and confirm the adoption was successful
            Pet updatedPet = petService.findPetById(idPet);  // Assuming we have to fetch the updated pet
            updatedPet.setIsAdopted("true");  // Assuming there's a field to set adoption status
            petService.updatePet(updatedPet);  // Save the updated pet details

            // Return a 200 OK response if everything was successful
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions, return a 404 Not Found or 400 Bad Request if something goes wrong
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
