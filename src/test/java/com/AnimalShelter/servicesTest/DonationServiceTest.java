package com.AnimalShelter.servicesTest;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.repositories.IDonationRepository;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.services.DonationService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

}
