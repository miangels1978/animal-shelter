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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;
@AutoConfigureMockMvc

@WebMvcTest(PetController.class)
@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;
    @MockBean
    private JwtService jwtService;


    @InjectMocks
    private PetController petController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    public void testAddNewPet() throws Exception {
        Pet pet = new Pet();
        pet.setIdPet(1L);
        pet.setName("Balud");
        pet.setSpecies("Golden Retriever");
        pet.setAge(5);
        pet.setGender("Male");
        pet.setDescription("Ejemplar");
        pet.setIsAdopted(false);
        pet.setUrl("http://example.com/balud.jpg");

        when(petService.addNewPet(any(Pet.class))).thenReturn(pet);

        Pet addNewPet = petService.addNewPet(pet);

        assertNotNull(addNewPet);
        assertEquals(1L, addNewPet.getIdPet());
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
    public void test_if_deleteByID_deletes_pet() throws Exception {
        when(petService.getPetById(1L)).thenReturn(Optional.of(new Pet()));

        mockMvc.perform(delete("/api/v1/pets/pet/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Pet deleted successfully"));

        verify(petService).deletePetById(1L);
    }

    @Test
    public void test_if_deleteByID_deletes_all_pets() throws Exception {
        doNothing().when(petService).deleteAllPets();

        mockMvc.perform(delete("/api/v1/pets"))
                .andExpect(status().isOk());

        verify(petService).deleteAllPets();
    }
    @Test
    public void updatePet() throws Exception {
        Pet updatedPet = new Pet();
        updatedPet.setIdPet(1L);
        updatedPet.setName("Updated Pet");
        when(petService.updatePet(any(Pet.class))).thenReturn(updatedPet);

        ResponseEntity<Pet> response = petController.updatePet(1L, updatedPet);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Pet", response.getBody().getName());
        verify(petService, times(1)).updatePet(any(Pet.class));
    }

}



