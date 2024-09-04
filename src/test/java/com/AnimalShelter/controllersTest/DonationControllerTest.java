package com.AnimalShelter.controllersTest;

import com.AnimalShelter.controllers.DonationController;
import com.AnimalShelter.models.Donation;
import com.AnimalShelter.services.DonationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.AnimalShelter.services.JwtService;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)

@WebMvcTest(DonationController.class)

public class DonationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DonationService donationService;
    private ObjectMapper objectMapper;

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
        donation.setAmount (100.0d);

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
    @Test
    public void testCreateDonation() throws Exception {
        Donation donation = new Donation ();
        donation.setId (1L);
        donation.setName ("Juan");
        donation.setAmount(300.0d);

        when(donationService.createDonation (any(Donation.class))).thenReturn(donation);

        Donation createdDonation = donationService.createDonation (donation);

        assertNotNull(createdDonation);
        assertEquals(1L,createdDonation.getId () );
    }
}

