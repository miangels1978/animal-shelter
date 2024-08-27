package com.AnimalShelter.services;

import com.AnimalShelter.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    IUserRepository iUserRepository;

    public void deleteUser(Long id){
        iUserRepository.deleteById(id);
    }
    public void deleteAllUsers(){
        iUserRepository.deleteAll();
    }
}
