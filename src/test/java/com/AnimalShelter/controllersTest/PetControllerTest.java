package com.AnimalShelter.controllersTest;

import com.AnimalShelter.controllers.PetController;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.services.PetService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(PetController.class)

public class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @InjectMocks
    private PetController petController;

    @Test
    public void testGetAllAvailablePets() {

        Pet pet1 = new Pet();
        pet1.setIdPet(1L);
        pet1.setName("Buddy");
        pet1.setIsAdopted("false");

        Pet pet2 = new Pet();
        pet2.setIdPet(2L);
        pet2.setName("Max");
        pet2.setIsAdopted("false");

        List<Pet> availablePets = Arrays.asList(pet1, pet2);

        when(petService.getAllAvailablePets()).thenReturn(availablePets);

        List<Pet> result = petController.getAllAvailablePets();

        assertEquals(2, result.size());
        assertEquals("Buddy", result.get(0).getName());
        assertEquals("Max", result.get(1).getName());
    }

    @Test
    public void testGetAllAdoptedPets() {

        Pet pet1 = new Pet();
        pet1.setIdPet(1L);
        pet1.setName("Lucy");
        pet1.setIsAdopted("true");

        Pet pet2 = new Pet();
        pet2.setIdPet(2L);
        pet2.setName("Bella");
        pet2.setIsAdopted("true");

        List<Pet> adoptedPets = Arrays.asList(pet1, pet2);

        when(petService.getAllAdoptedPets()).thenReturn(adoptedPets);

        List<Pet> result = petController.getAllAdoptedPets();

        assertEquals(2, result.size());
        assertEquals("Lucy", result.get(0).getName());
        assertEquals("Bella", result.get(1).getName());
    }

    @Test
    public void testGetAllPets() {

        Pet pet1 = new Pet();
        pet1.setIdPet(1L);
        pet1.setName("Buddy");

        Pet pet2 = new Pet();
        pet2.setIdPet(2L);
        pet2.setName("Max");

        List<Pet> allPets = Arrays.asList(pet1, pet2);

        when(petService.getAllPets()).thenReturn(allPets);

        List<Pet> result = petController.getAllPets();

        assertEquals(2, result.size());
        assertEquals("Buddy", result.get(0).getName());
        assertEquals("Max", result.get(1).getName());
    }
    @Test
    public void testGetPetById() {
        long id = 1L;
        Pet pet = new Pet();
        pet.setIdPet(id);
        pet.setName("Buddy");

        when(petService.getPetById(id)).thenReturn(Optional.of(pet));

        Optional<Pet> result = petController.getPetById(id);

        assertEquals(true, result.isPresent());
        assertEquals("Buddy", result.get().getName());
    }
}

