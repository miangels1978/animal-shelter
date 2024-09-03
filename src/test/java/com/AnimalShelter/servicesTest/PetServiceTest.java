package com.AnimalShelter.servicesTest;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.services.PetService;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PetServiceTest {
    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private PetService petService;

    @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void testGetAllAvailablePets() {

            List<Pet> availablePets = new ArrayList<>();
            when(iPetRepository.findByIsAdoptedFalse()).thenReturn(availablePets);

            List<Pet> result = petService.getAllAvailablePets();

            assertEquals(availablePets, result);
            verify(iPetRepository, times(1)).findByIsAdoptedFalse();
        }

        @Test
        void testGetAllAdoptedPets() {

            List<Pet> adoptedPets = new ArrayList<>();
            when(iPetRepository.findByIsAdoptedTrue()).thenReturn(adoptedPets);

            List<Pet> result = petService.getAllAdoptedPets();

            assertEquals(adoptedPets, result);
            verify(iPetRepository, times(1)).findByIsAdoptedTrue();
        }

        @Test
        void testGetAllPets() {

            List<Pet> allPets = new ArrayList<>();
            when(iPetRepository.findAll()).thenReturn(allPets);

            List<Pet> result = petService.getAllPets();

            assertEquals(allPets, result);
            verify(iPetRepository, times(1)).findAll();
        }

        @Test
        void testGetPetById() {

            Pet pet = new Pet();
            when(iPetRepository.findById(1L)).thenReturn(Optional.of(pet));

            Optional<Pet> result = petService.getPetById(1L);

            assertEquals(Optional.of(pet), result);
            verify(iPetRepository, times(1)).findById(1L);
        }
}

