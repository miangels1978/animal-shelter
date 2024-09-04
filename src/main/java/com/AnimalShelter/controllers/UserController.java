package com.AnimalShelter.controllers;

import com.AnimalShelter.models.User;
import com.AnimalShelter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")

public class UserController {
    @Autowired
    UserService userService;

    @GetMapping (path = "")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    @GetMapping (path = "/User")
    public Optional<User> getUserById(long id){
        return userService.getUserById(id);
    }


}
