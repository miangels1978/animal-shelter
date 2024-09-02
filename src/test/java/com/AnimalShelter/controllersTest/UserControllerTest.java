package com.AnimalShelter.controllersTest;

import com.AnimalShelter.controllers.UserController;
import com.AnimalShelter.models.ERole;
import com.AnimalShelter.models.User;
import com.AnimalShelter.services.JwtService;
import com.AnimalShelter.services.UserService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)

public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtService jwtService;

    @InjectMocks
    private UserController userController;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks (this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();

    }
    @Test
    public void testCreateUser1() throws Exception {
        User user = new User(); // inicializa tu usuario
        mockMvc.perform(MockMvcRequestBuilders.post("/user/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void testCreateUser() throws Exception {
        // Configurar el objeto User
        User user = new User ();
        user.setIdUser(2L);
        user.setUsername ("miangels");
        user.setEmail ("ma@gmail.com");
        user.setPassword ("123456F5");
        user.setRole (ERole.USER);

        System.out.println(objectMapper.writeValueAsString(user));

        // Configurar el comportamiento simulado del servicio
        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform (post("/user/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)  // <-- Este es el header que puedes añadir
                        .content(objectMapper.writeValueAsString(user)))
                        .andExpect(status().isOk())
                        .andExpect(content().json(objectMapper.writeValueAsString(user)))
                        .andExpect(content().contentType(MediaType. APPLICATION_JSON))
                        .andExpect(jsonPath("$.person. name").value("Jason"));
        }

}
