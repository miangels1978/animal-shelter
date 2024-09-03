package com.AnimalShelter.controllersTest;

import com.AnimalShelter.controllers.UserController;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.models.User;
import com.AnimalShelter.models.ERole;
import com.AnimalShelter.services.PetService;
import com.AnimalShelter.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PetService petService;

    private User user;
    private Pet pet;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setIdUser(1L);
        user.setUsername("AlessiaR");
        user.setEmail("aapromero@gmail.com");
        user.setPassword("ale08");
        user.setRole(ERole.USER);

        pet = new Pet();
        pet.setIdPet(1L);
        pet.setName("Bella");
        pet.setIsAdopted("false");
    }

    @Test
    public void testGetUserById_Success() throws Exception {
        when(userService.findUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUser").value(1L))
                .andExpect(jsonPath("$.username").value("AlessiaR"))
                .andExpect(jsonPath("$.email").value("aapromero@gmail.com"))
                .andExpect(jsonPath("$.password").value("ale08"))
                .andExpect(jsonPath("$.role").value("USER"));
    }

    @Test
    public void testGetUserById_NotFound() throws Exception {
        when(userService.findUserById(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/users/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateUser_Success() throws Exception {
        when(userService.updateUser(any(User.class))).thenReturn(user);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"AlessiaR\",\"email\":\"aapromero@gmail.com\",\"password\":\"ale08\",\"role\":\"USER\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUser").value(1L))
                .andExpect(jsonPath("$.username").value("AlessiaR"))
                .andExpect(jsonPath("$.email").value("aapromero@gmail.com"))
                .andExpect(jsonPath("$.password").value("ale08"))
                .andExpect(jsonPath("$.role").value("USER"));
    }

    @Test
    public void testUpdateUser_NotFound() throws Exception {
        when(userService.updateUser(any(User.class))).thenReturn(null);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"AlessiaR\",\"email\":\"aapromero@gmail.com\",\"password\":\"ale08\",\"role\":\"USER\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAdoptPet_Success() throws Exception {
        when(petService.findPetById(1L)).thenReturn(pet);
        when(petService.updatePet(any(Pet.class))).thenReturn(pet);

        mockMvc.perform(post("/users/1/adopt/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAdoptPet_NotFound() throws Exception {
        Mockito.doThrow(new RuntimeException()).when(petService).adoptPet(1L, 1L);

        mockMvc.perform(post("/users/1/adopt/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
