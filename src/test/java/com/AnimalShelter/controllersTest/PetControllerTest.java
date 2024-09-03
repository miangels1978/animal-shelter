package com.AnimalShelter.controllersTest;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.services.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetControllerTest.class)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @Test
    void getPetById() throws Exception {
        Pet pet = new Pet();
        pet.setIdPet(1L);
        when(petService.findPetById(1L)).thenReturn(pet);

        mockMvc.perform(get("/api/v1/pets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPet").value(1));
    }

    @Test
    void updatePet() throws Exception {
        Pet updatedPet = new Pet();
        updatedPet.setIdPet(1L);
        updatedPet.setName("Updated Pet");
        when(petService.updatePet(any(Pet.class))).thenReturn(updatedPet);

        mockMvc.perform(put("/api/v1/pets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Pet\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Pet"));
    }

    @Test
    void adoptPet() throws Exception {
        doNothing().when(petService).adoptPet(1L, 2L);

        mockMvc.perform(post("/api/v1/pets/1/adopt/2"))
                .andExpect(status().isOk());
    }

    @Test
    void getPetById_NotFound() throws Exception {
        when(petService.findPetById(1L)).thenThrow(new RuntimeException("Pet not found"));

        mockMvc.perform(get("/api/v1/pets/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void adoptPet_NotFound() throws Exception {
        doThrow(new RuntimeException("Pet or User not found")).when(petService).adoptPet(1L, 2L);

        mockMvc.perform(post("/api/v1/pets/1/adopt/2"))
                .andExpect(status().isNotFound());
    }
}
