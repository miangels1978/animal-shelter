package com.AnimalShelter.repositories;

import com.AnimalShelter.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface IPetRepository extends CrudRepository<Pet, Long> {
}
