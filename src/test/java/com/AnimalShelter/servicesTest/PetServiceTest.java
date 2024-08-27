package com.AnimalShelter.servicesTest;

import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.services.PetService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class PetServiceTest {
    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private PetService petService;
}
