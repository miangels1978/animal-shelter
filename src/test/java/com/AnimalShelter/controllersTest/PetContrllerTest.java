package com.AnimalShelter.controllersTest;

import com.AnimalShelter.controllers.DonationController;
import com.AnimalShelter.controllers.PetController;
import com.AnimalShelter.models.Donation;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.services.DonationService;
import com.AnimalShelter.services.PetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
@ExtendWith(MockitoExtension.class)
public class PetContrllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PetController petController;

    @MockBean
    private PetService petService;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(petController)
                .build();
    }

    @Test
    public void test_if_deleteByID_deletes_pet() throws Exception {
        when(petService.findById(1L)).thenReturn(Optional.of(new Pet()));

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
}
