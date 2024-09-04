package com.AnimalShelter.controllersTest;

import com.AnimalShelter.controllers.DonationController;
import com.AnimalShelter.models.Donation;
import com.AnimalShelter.models.User;
import com.AnimalShelter.repositories.IDonationRepository;
import com.AnimalShelter.services.DonationService;
//import com.AnimalShelter.services.JwtService;
import com.AnimalShelter.services.JwtService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(DonationController.class)

public class DonationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private DonationController donationController;

    @MockBean
    private DonationService donationService;
    private ObjectMapper objectMapper;
    @MockBean
    private JwtService jwtService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(donationController)
                .build();
    }

    @Test
    public void test_if_deleteByID_deletes_all_donations() throws Exception {
        when(donationService.findById(1L)).thenReturn(Optional.of(new Donation()));

        mockMvc.perform(delete("/api/v1/donations/donation/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Donation deleted successfully"));

        verify(donationService).deleteDonationById(1L);
    }
    @Test
    public void test_if_deleteByID_deletes_donation() throws Exception{
        doNothing().when(donationService).deleteAllDonations();

        mockMvc.perform(delete("/api/v1/donations"))
                .andExpect(status().isOk());

        verify(donationService).deleteAllDonations();
    }
}

