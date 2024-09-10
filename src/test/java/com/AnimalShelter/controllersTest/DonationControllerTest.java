package com.AnimalShelter.controllersTest;

import com.AnimalShelter.controllers.DonationController;
import com.AnimalShelter.models.Donation;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.services.DonationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class DonationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DonationService donationService;

    @InjectMocks
    private DonationController donationController;

    @BeforeEach
    void setUp() {
        openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(donationController).build();
    }

    @Test
    public void testGetAllDonation() throws Exception {

        Donation donation = new Donation();
        donation.setId(1L);
        donation.setName("Donation for food");
        donation.setAmount("100");

        when(donationService.getAllDonation()).thenReturn(Collections.singletonList(donation));

        mockMvc.perform(get("/api/v1/donations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Donation for food"))
                .andExpect(jsonPath("$[0].amount").value("100"));
    }

    @Test
    public void testGetDonationById() throws Exception {

        long id = 1L;
        Donation donation = new Donation();
        donation.setId(id);

        when(donationService.getDonationById(id)).thenReturn(Optional.of(donation));

        ResponseEntity<Donation> response = donationController.getDonationById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(donation, response.getBody());
    }
}


