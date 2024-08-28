package com.AnimalShelter.servicesTest;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.repositories.IDonationRepository;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.services.DonationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
    public void testCreateDonation() {

        Donation donationToSave = new Donation();
        Long id = 1L;
        String name = "Miguel Angel";
        double amount = 250;

        donationToSave.setId(id);
        donationToSave.setName(name);
        donationToSave.setAmount(amount);

        when(iDonationRepository.save(donationToSave)).thenReturn(donationToSave);

        Donation result = donationService.createDonation(donationToSave);

        assertEquals(1L, result.getId());
        assertEquals("Miguel Angel", result.getName());
        assertEquals(250, result.getAmount());

        verify(iDonationRepository, times(1)).save(donationToSave);
    }

}
