package com.AnimalShelter.controllersTest;

import com.AnimalShelter.controllers.UserController;
import com.AnimalShelter.models.User;
import com.AnimalShelter.models.ERole;
import com.AnimalShelter.services.JwtService;
import com.AnimalShelter.services.UserService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    private ObjectMapper objectMapper;

    @MockBean
    private JwtService jwtService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateNewUser() throws Exception {
        User user = new User();
        user.setIdUser(2L);
        user.setUsername("miangels");
        user.setEmail("ma@gmail.com");
        user.setPassword("123456F5");
        user.setRole(ERole.USER);

        when(userService.createUser(any(User.class))).thenReturn(user);
    }

    @Test
    public void testGetAllUser() {
        User user1 = new User();
        user1.setIdUser(1L);
        user1.setUsername("John");
        User user2 = new User();
        user2.setIdUser(2L);
        user2.setUsername("Jane");
        List<User> userList = Arrays.asList(user1, user2);
        when(userService.getAllUser()).thenReturn(userList);

        List<User> result = userService.getAllUser();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getUsername());
        assertEquals("Jane", result.get(1).getUsername());
    }

    @Test
    public void testGetUserById() {
        long id = 1L;
        User user = new User();
        user.setIdUser(id);
        user.setUsername("John");
        when(userService.getUserById(id)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(id);

        assertEquals(true, result.isPresent());
        assertEquals("John", result.get().getUsername());
    }

    @Test
    public void test_if_deleteByID_deletes_user() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(new User()));

        mockMvc.perform(delete("/api/v1/users/user/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted successfully"));

        verify(userService).deleteUserById(1L);
    }

    @Test
    public void test_if_deleteByID_deletes_all_users() throws Exception {
        doNothing().when(userService).deleteAllUsers();

        mockMvc.perform(delete("/api/v1/users"))
                .andExpect(status().isOk());

        verify(userService).deleteAllUsers();
    }

    @Test
    void getUserById_NotFound() throws Exception {
        when(userService.findUserById(1L)).thenThrow(new RuntimeException("User not found"));

        mockMvc.perform(get("/{idUser}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateUser() throws Exception {
        User updatedUser = new User();
        updatedUser.setIdUser(1L);
        updatedUser.setUsername("john_updated");
        updatedUser.setEmail("john_updated@example.com");
        when(userService.updateUser(any(User.class))).thenReturn(updatedUser);

        ResponseEntity<User> response = userController.updateUser(1L, updatedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("john_updated", response.getBody().getUsername());
        assertEquals("john_updated@example.com", response.getBody().getEmail());
        verify(userService, times(1)).updateUser(any(User.class));
    }

    @Test
    public void updateUser_NotFound() throws Exception {
        when(userService.updateUser(any(User.class))).thenReturn(null);

        User updatedUser = new User();
        updatedUser.setIdUser(1L);
        ResponseEntity<User> response = userController.updateUser(1L, updatedUser);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(userService, times(1)).updateUser(any(User.class));
    }
}
