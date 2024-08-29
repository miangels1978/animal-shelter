package com.AnimalShelter.services;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.models.User;
import com.AnimalShelter.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepository iUserRepository;

    public Optional<User> findById(Long id){
        return iUserRepository.findById(id);
    }
    public void deleteUserById(Long id){
        iUserRepository.deleteById(id);
    }
    public void deleteAllUsers(){
        iUserRepository.deleteAll();
    }
}
