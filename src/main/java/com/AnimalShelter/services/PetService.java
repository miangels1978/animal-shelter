package com.AnimalShelter.services;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.repositories.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    @Autowired
    IPetRepository iPetRepository;

    public List<Pet> getAllAvailablePets() {
        return iPetRepository.findByIsAdoptedFalse();
    }

    public List<Pet> getAllAdoptedPets() {
        return iPetRepository.findByIsAdoptedTrue();
    }

//    public List<Pet> getAllAvailablePets() {
//        return iPetRepository.findAll()
//                .stream()
//                .filter(pet -> !pet.getIsAdopted())
//                .collect(Collectors.toList());
//    }
//
//    public List<Pet> getAllAdoptedPets() {
//        return iPetRepository.findAll()
//                .stream()
//                .filter(pet -> !pet.getIsAdopted())
//                .collect(Collectors.toList());
//    }

//    public List<Pet> getAllAvailablePets(){
//        try{
//            return iPetRepository.findAll();
//        } catch (Exception e){
//            throw new RuntimeException("Error retrieving all available pets.", e);
//        }
//    }
//
//    public List<Pet> getAllAdoptedPets() {
//        try{
//            return iPetRepository.findAll();
//        } catch (Exception e){
//            throw new RuntimeException("Error retrieving all adopted pets.", e);
//        }
//    }

    public List<Pet> getAllPets(){
        try{
            return (List<Pet>) iPetRepository.findAll();
        } catch (Exception e){
            throw new RuntimeException("Error retrieving all pets.", e);
        }
    }

    public Optional<Pet> getPetById(long id){
        try{
            return iPetRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving pet by id.", e);
        }
    }
}


