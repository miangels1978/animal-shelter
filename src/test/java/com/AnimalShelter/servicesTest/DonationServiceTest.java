package com.AnimalShelter.servicesTest;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.repositories.IDonationRepository;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.services.DonationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DonationServiceTest {

    @Mock
    private IDonationRepository iDonationRepository;
    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private DonationService donationService;
    private Donation donation;
    private Pet pet;

    @BeforeEach
        void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDonation() {

        List<Donation> donations = new ArrayList<>();
        donations.add(new Donation(1L, 100, "John Doe", null));
        donations.add(new Donation(2L, 200, "Jane Smith", null));

        when(iDonationRepository.findAll()).thenReturn(donations);

        List<Donation> result = donationService.getAllDonation();

        assertEquals(donations, result);
        verify(iDonationRepository, times(1)).findAll();
    }

    @Test
    void testGetDonationById() {

        Donation donation = new Donation(1L, 100, "John Doe", null);
        when(iDonationRepository.findById(1L)).thenReturn(Optional.of(donation));

        Optional<Donation> result = donationService.getDonationById(1L);

        assertEquals(Optional.of(donation), result);
        verify(iDonationRepository, times(1)).findById(1L);
    }
}


