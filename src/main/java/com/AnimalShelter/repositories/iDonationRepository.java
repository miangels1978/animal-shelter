package com.AnimalShelter.repositories;

import com.AnimalShelter.models.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iDonationRepository extends JpaRepository<Donation, Long> {
}
