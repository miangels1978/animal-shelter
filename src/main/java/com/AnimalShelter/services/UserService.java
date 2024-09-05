package com.AnimalShelter.services;


import com.AnimalShelter.models.User;
import com.AnimalShelter.models.User;
import com.AnimalShelter.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepository iUserRepository;

    public User createUser(User user) {
        return iUserRepository.save(user);
    }

    public List<User> getAllUser() {
        try {
            return iUserRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all user", e);
        }
    }

    public Optional<User> getUserById(long id) {
        try {
            return iUserRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all user by id", e);
        }
    }

    public void deleteUserById(Long id) {
        iUserRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        iUserRepository.deleteAll();
    }


    public User findUserById(Long idUser) {
        return iUserRepository.findById(idUser).orElseThrow();
    }

    public User updateUser(User updatedUser) {
        Optional<User> existingUser = iUserRepository.findById(updatedUser.getIdUser());
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());
            return iUserRepository.save(user);
        }
        return null;
    }
}