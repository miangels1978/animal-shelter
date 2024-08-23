package com.AnimalShelter.modelsTest;

import com.AnimalShelter.controllers.PetController;
import com.AnimalShelter.services.PetService;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PetController.class)

public class PetTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @InjectMocks
    private PetController petController;
}
