package com.AnimalShelter.controllers;

import com.AnimalShelter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")

public class UserController {
    @Autowired
    UserService userService;
}
