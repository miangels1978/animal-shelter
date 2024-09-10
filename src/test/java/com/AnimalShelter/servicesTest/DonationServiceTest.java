package com.AnimalShelter.servicesTest;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.repositories.IDonationRepository;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.services.DonationService;
import com.AnimalShelter.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class DonationServiceTest {

    @Mock
    private IDonationRepository iDonationRepository;
    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private DonationService donationService;
    private Donation donation;
    private Pet pet;

    @BeforeEach
        void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDonation() {

        List<Donation> donations = new ArrayList<>();
        donations.add(new Donation(1L, "John Doe", "100", null));
        donations.add(new Donation(2L, "Jane Smith", "200", null));

        when(iDonationRepository.findAll()).thenReturn(donations);

        List<Donation> result = donationService.getAllDonation();

        assertEquals(donations, result);
        verify(iDonationRepository, times(1)).findAll();
    }

    @Test
    void testGetDonationById() {

        Donation donation = new Donation(1L, "John Doe", "100", null);
        when(iDonationRepository.findById(1L)).thenReturn(Optional.of(donation));

        Optional<Donation> result = donationService.getDonationById(1L);

        assertEquals(Optional.of(donation), result);
        verify(iDonationRepository, times(1)).findById(1L);
    }
    @Test
    void testGetAllAvailablePets_WhenPetsAreAvailable() {
        List<Pet> petList = new ArrayList<>();  // Inicializa la lista de mascotas

        // Mascotas de prueba
        Pet pet1 = new Pet();
        pet1.setAdopted(false);  // Mascota no adoptada

        Pet pet2 = new Pet();
        pet2.setAdopted(true);  // Mascota adoptada

        Pet pet3 = new Pet();
        pet3.setAdopted(false);  // Mascota no adoptada

        petList.add(pet1);
        petList.add(pet2);
        petList.add(pet3);
        // Configurar el mock para devolver todas las mascotas

        when(iPetRepository.findAll()).thenReturn(petList);

        // Llamar al método del servicio
        List<Pet> availablePets = PetService.getAllAvailablePets();

        // Verificar el tamaño de la lista devuelta (debe ser 2, ya que hay 2 mascotas no adoptadas)
        assertEquals(2, availablePets.size());

        // Verificar que las mascotas devueltas no están adoptadas
        assertTrue(availablePets.stream().allMatch(pet -> !pet.isAdopted()));

        // Verificar que el repositorio fue llamado una vez
        verify(iPetRepository, times(1)).findAll();

    }
}


