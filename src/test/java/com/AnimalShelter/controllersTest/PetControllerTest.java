package com.AnimalShelter.controllersTest;

import com.AnimalShelter.controllers.PetController;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.services.JwtService;
import com.AnimalShelter.services.PetService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc

public class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PetService petService;

    @ExtendWith(SpringExtension.class)

    @MockBean
    private JwtService jwtService;


    @InjectMocks
    private PetController petController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks (this);
        mockMvc = MockMvcBuilders.standaloneSetup (petController).build ();
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

    @Test
    public void testAddNewPet() throws Exception {
        Pet pet = new Pet ();
        pet.setIdPet (1L);
        pet.setName ("Balud");
        pet.setSpecies ("Golden Retriever");
        pet.setAge (5);
        pet.setGender ("Male");
        pet.setDescription ("Ejemplar");
        pet.setIsadopted (false);
        pet.setUrl ("http://example.com/balud.jpg");

        when (petService.addNewPet (any (Pet.class))).thenReturn (pet);

        Pet addNewPet = petService.addNewPet (pet);

        assertNotNull (addNewPet);
        assertEquals (1L, addNewPet.getIdPet ());
    }
}
