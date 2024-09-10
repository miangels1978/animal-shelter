package com.AnimalShelter.controllers;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.models.User;
import com.AnimalShelter.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/donations")
@CrossOrigin(origins = "*")

public class DonationController {
    @Autowired
    DonationService donationService;

    @GetMapping
    public List<Donation> getAllDonation(){
        return donationService.getAllDonation();
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Donation> getDonationById(@PathVariable long id){
        return donationService.getDonationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Map<User, Integer> getTopDonors(List<User> users) {
        Map<User, Integer> donationPerUser = new HashMap<>();

        // Iterar todos los usuarios
        for (User user : users) {
            int totalDonations = 0;

            // Iterar todas las donaciones de cada usuario
            for (Donation donation : user.getDonationList()) {
                totalDonations += donation.getAmount();
            }

            // Guardar el total de donaciones por usuario en el mapa
            donationPerUser.put(user, totalDonations);
        }

        // Ordenar las entradas del diccionario de mayor a menor según totalDonations
        List<Map.Entry<User, Integer>> sortedDonors = new ArrayList<>(donationPerUser.entrySet());
        sortedDonors.sort(Map.Entry.<User, Integer>comparingByValue().reversed());

        // Devolver un LinkedHashMap para mantener el orden de mayor a menor
        Map<User, Integer> sortedDonationMap = new LinkedHashMap<>();
        for (Map.Entry<User, Integer> entry : sortedDonors) {
            sortedDonationMap.put(entry.getKey(), entry.getValue());
        }

        return sortedDonationMap;
    }

    public User findTopDonor(List<User> users) {
        User topDonor = null;
        int maxDonationAmount = 0;

        // Iterar todos los usuarios
        for (User user : users) {
            int totalDonations = 0;

            // Iterar todas las donaciones de cada usuario
            for (Donation donation : user.getDonationList()) {
                totalDonations += donation.getAmount();
            }

            // Verificar si este usuario tiene una donación mayor
            if (totalDonations > maxDonationAmount) {
                maxDonationAmount = totalDonations;
                topDonor = user;
            }
        }

        // Devolver el usuario con la mayor cantidad donada
        return topDonor;
    }

}
