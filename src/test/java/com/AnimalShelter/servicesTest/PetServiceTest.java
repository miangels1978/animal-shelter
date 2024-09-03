package com.AnimalShelter.servicesTest;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.services.PetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {
    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private PetService petService;

    @Test
    public void testAddNewPet() {
        Pet pet = new Pet();
        pet.setIdPet(1L);
        pet.setName("Balud");
        pet.setSpecies("Golden Retriever");
        pet.setAge(5);
        pet.setGender("Male");
        pet.setDescription("Ejemplar");
        pet.setAdopted(false);
        pet.setUrl("http://example.com/balud.jpg");

        when(iPetRepository.save(any(Pet.class))).thenReturn(pet);

        Pet result = petService.addNewPet(pet);

        assertEquals(1L, result.getIdPet());
        assertEquals("Balud", result.getName());
        assertEquals("Golden Retriever", result.getSpecies());
        assertEquals(5, result.getAge());
        assertEquals("Male", result.getGender());
        assertEquals("Ejemplar", result.getDescription());
        assertEquals(false, result.getAdopted());
        assertEquals("http://example.com/balud.jpg", result.getUrl());
    }
}
