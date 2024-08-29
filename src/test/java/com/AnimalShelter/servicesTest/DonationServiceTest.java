package com.AnimalShelter.servicesTest;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.models.User;
import com.AnimalShelter.repositories.IDonationRepository;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.services.DonationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DonationServiceTest {

    @Mock
    private IDonationRepository iDonationRepository;
    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private DonationService donationService;
    private Donation donation;
    private Pet pet;

    @Test
    public void test_if_delete_by_id_deletes_donation(){
        Long id = 1L;
        doNothing().when(iDonationRepository).deleteById(id);

        donationService.deleteDonationById(id);

        verify(iDonationRepository).deleteById(id);
    }
    @Test
    public void test_if_deleteAllDonation_deletes(){

        // user1 = new User();
        //Donation donation1 = new Donation(1L, "pepita", 123.2, user1);
        //Donation donation2 = new Donation(1L, "pepita", 123.2, user1);
        //List<Donation> donationList = new ArrayList<>(Arrays.asList(donation1, donation2));
        doNothing().when(iDonationRepository).deleteAll();

        donationService.deleteAllDonations();

        verify(iDonationRepository).deleteAll();

    }
}
