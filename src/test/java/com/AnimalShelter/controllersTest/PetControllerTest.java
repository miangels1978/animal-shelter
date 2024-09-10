package com.AnimalShelter.controllersTest;

import com.AnimalShelter.controllers.PetController;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PetControllerTest {

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAvailablePets() {
        List<Pet> availablePets = new ArrayList<>();
        when(petService.getAllAvailablePets()).thenReturn(availablePets);

        List<Pet> result = petController.getAllAvailablePets();
        assertEquals(availablePets, result);
        verify(petService, times(1)).getAllAvailablePets();
    }

    @Test
    void testGetAllAdoptedPets() {
        List<Pet> adoptedPets = new ArrayList<>();
        when(petService.getAllAdoptedPets()).thenReturn(adoptedPets);

        List<Pet> result = petController.getAllAdoptedPets();
        assertEquals(adoptedPets, result);
        verify(petService, times(1)).getAllAdoptedPets();
    }

    @Test
    void testGetAllPets() {
        List<Pet> pets = new ArrayList<>();
        when(petService.getAllPets()).thenReturn(pets);

        List<Pet> result = petController.getAllPets();
        assertEquals(pets, result);
        verify(petService, times(1)).getAllPets();
    }

    @Test
    void testGetPetById() {
        Pet pet = new Pet();
        when(petService.getPetById(1L)).thenReturn(Optional.of(pet));

        Optional<Pet> result = petController.getPetById(1L);
        assertEquals(Optional.of(pet), result);
        verify(petService, times(1)).getPetById(1L);
    }

}
