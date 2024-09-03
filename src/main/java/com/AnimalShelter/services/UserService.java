package com.AnimalShelter.services;

import com.AnimalShelter.models.User;
import com.AnimalShelter.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepository iUserRepository;

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
