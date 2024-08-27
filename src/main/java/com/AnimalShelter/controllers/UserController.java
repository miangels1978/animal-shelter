package com.AnimalShelter.controllers;

import com.AnimalShelter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")

public class UserController {
    @Autowired
    UserService userService;

    @DeleteMapping (path= "/user/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
