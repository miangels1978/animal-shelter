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

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void testAddNewPet() throws Exception {
        Pet pet = new Pet ();
        pet.setIdPet (1L);
        pet.setName ("Balud");
        pet.setSpecies ("Golden Retriever");
        pet.setAge (5);
        pet.setGender ("Male");
        pet.setDescription ("Ejemplar");
        pet.setAdopted (false);
        pet.setUrl ("http://example.com/balud.jpg");

        when (petService.addNewPet (any (Pet.class))).thenReturn (pet);

        Pet addNewPet = petService.addNewPet (pet);

        assertNotNull (addNewPet);
        assertEquals (1L, addNewPet.getIdPet ());
    }



}