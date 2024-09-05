package com.AnimalShelter.controllersTest;

import com.AnimalShelter.controllers.DonationController;
import com.AnimalShelter.models.Donation;
import com.AnimalShelter.services.DonationService;
import com.AnimalShelter.services.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
    public void testGetAllDonation() throws Exception {
        Donation donation = new Donation();
        donation.setId(1L);
        donation.setName("Donation for food");
        donation.setAmount(100.0d);
        when(donationService.getAllDonation()).thenReturn(Collections.singletonList(donation));

        mockMvc.perform(get("/api/v1/donations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Donation for food"))
                .andExpect(jsonPath("$[0].amount").value("100.0"));
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
        Donation donation = new Donation();
        donation.setId(1L);
        donation.setName("Juan");
        donation.setAmount(300.0d);
        when(donationService.createDonation(any(Donation.class))).thenReturn(donation);

        Donation createdDonation = donationService.createDonation(donation);

        assertNotNull(createdDonation);
        assertEquals(1L, createdDonation.getId());
    }

    @Test
    public void test_if_deleteByID_deletes_all_donations() throws Exception {
        when(donationService.getDonationById(1L)).thenReturn(Optional.of(new Donation()));

        mockMvc.perform(delete("/api/v1/donations/donation/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Donation deleted successfully"));

        verify(donationService).deleteDonationById(1L);
    }

    @Test
    public void test_if_deleteByID_deletes_donation() throws Exception {
        doNothing().when(donationService).deleteAllDonations();

        mockMvc.perform(delete("/api/v1/donations"))
                .andExpect(status().isOk());

        verify(donationService).deleteAllDonations();

    }
}

