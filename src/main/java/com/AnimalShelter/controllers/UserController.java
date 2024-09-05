package com.AnimalShelter.controllers;

import com.AnimalShelter.models.User;
import com.AnimalShelter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")

public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(path = "")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping(path = "/User")
    public Optional<User> getUserById(long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUserById(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with id " + id + " was not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

}
