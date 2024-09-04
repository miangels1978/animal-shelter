package com.AnimalShelter.services;

import com.AnimalShelter.models.User;
import com.AnimalShelter.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepository iUserRepository;

    public List<User> getAllUser(){
        try {
            return iUserRepository.findAll();
        } catch (Exception e){
            throw new RuntimeException("Error retrieving all user" , e);
        }
    }
    public Optional<User> getUserById(long id){
        try {
            return iUserRepository.findById(id);
        } catch (Exception e){
            throw new RuntimeException("Error retrieving all user by id", e);
        }
    }
    public User createUser(User user) {
        return iUserRepository.save(user);
    }
}
