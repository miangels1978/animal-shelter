package com.AnimalShelter.controllersTest;

import com.AnimalShelter.controllers.PetController;
import com.AnimalShelter.services.PetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PetController.class)
@ExtendWith(MockitoExtension.class)
public class PetContrllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;
    private ObjectMapper objectMapper;

    @InjectMocks
    private PetController petController;
}
