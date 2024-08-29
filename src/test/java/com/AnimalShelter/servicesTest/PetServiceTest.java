package com.AnimalShelter.servicesTest;

import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.services.PetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {
    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private PetService petService;

    @Test
    public void test_if_delete_by_id_deletes_pet(){
        Long id = 1L;
        doNothing().when(iPetRepository).deleteById(id);

        petService.deletePetById(id);

        verify(iPetRepository).deleteById(id);
    }
    @Test
    public void test_if_deleteAllPets_deletes(){

        // user1 = new User();
        //Donation donation1 = new Donation(1L, "pepita", 123.2, user1);
        //Donation donation2 = new Donation(1L, "pepita", 123.2, user1);
        //List<Donation> donationList = new ArrayList<>(Arrays.asList(donation1, donation2));
        doNothing().when(iPetRepository).deleteAll();

        petService.deleteAllPets();

        verify(iPetRepository).deleteAll();

    }
}
