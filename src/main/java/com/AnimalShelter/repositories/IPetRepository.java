package com.AnimalShelter.repositories;

import com.AnimalShelter.models.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPetRepository extends CrudRepository<Pet, Long> {
    List<Pet> findByIsadoptedFalse();

    List<Pet> findByIsadoptedTrue();


    }


