package com.AnimalShelter.controllersTest;

import com.AnimalShelter.controllers.UserController;
import com.AnimalShelter.models.User;
import com.AnimalShelter.services.UserService;
import org.junit.jupiter.api.Test;
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
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getUserById() throws Exception {
        User user = new User();
        user.setIdUser(1L);
        user.setUsername("Alessia");
        when(userService.findUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/{idUser}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUser").value(1))
                .andExpect(jsonPath("$.username").value("Alessia"));
    }

    @Test
    void getUserById_NotFound() throws Exception {
        when(userService.findUserById(1L)).thenThrow(new RuntimeException("User not found"));

        mockMvc.perform(get("/{idUser}"))
                .andExpect(status().isNotFound());

    @Test
    void updateUser() throws Exception {
        User updatedUser = new User();
        updatedUser.setIdUser(1L);
        updatedUser.setUsername("john_updated");
        updatedUser.setEmail("john_updated@example.com");
        when(userService.updateUser(any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/update/{idUser})
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"john_updated\", \"email\": \"john_updated@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john_updated"))
                .andExpect(jsonPath("$.email").value("john_updated@example.com"));

    }
}