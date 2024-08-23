package com.AnimalShelter.repositories;

import com.AnimalShelter.models.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDonationRepository extends JpaRepository<Donation, Long> {
}
