package com.AnimalShelter.servicesTest;

import com.AnimalShelter.models.Pet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {
    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private PetService petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewPet() {
        Pet pet = new Pet();
        pet.setIdPet(1L);
        pet.setName("Balud");
        pet.setSpecies("Golden Retriever");
        pet.setAge(5);
        pet.setGender("Male");
        pet.setDescription("Ejemplar");
        pet.setIsadopted(false);
        pet.setUrl("http://example.com/balud.jpg");
        when(iPetRepository.save(any(Pet.class))).thenReturn(pet);

        Pet result = petService.addNewPet(pet);

        assertEquals(1L, result.getIdPet());
        assertEquals("Balud", result.getName());
        assertEquals("Golden Retriever", result.getSpecies());
        assertEquals(5, result.getAge());
        assertEquals("Male", result.getGender());
        assertEquals("Ejemplar", result.getDescription());
        assertEquals(false, result.getIsadopted());
        assertEquals("http://example.com/balud.jpg", result.getUrl());
    }

    @Test
    void testGetAllAvailablePets() {
        List<Pet> availablePets = new ArrayList<>();
        when(iPetRepository.findByIsadoptedFalse()).thenReturn(availablePets);

        List<Pet> result = petService.getAllAvailablePets();

        assertEquals(availablePets, result);
        verify(iPetRepository, times(1)).findByIsadoptedFalse();
    }

    @Test
    void testGetAllAdoptedPets() {
        List<Pet> adoptedPets = new ArrayList<>();
        when(iPetRepository.findByIsadoptedTrue()).thenReturn(adoptedPets);

        List<Pet> result = petService.getAllAdoptedPets();

        assertEquals(adoptedPets, result);
        verify(iPetRepository, times(1)).findByIsadoptedTrue();
    }

    @Test
    void testGetAllPets() {
        List<Pet> allPets = new ArrayList<>();
        when(iPetRepository.findAll()).thenReturn(allPets);

        List<Pet> result = petService.getAllPets();

        assertEquals(allPets, result);
        verify(iPetRepository, times(1)).findAll();
    }

    @Test
    void testGetPetById() {
        Pet pet = new Pet();
        when(iPetRepository.findById(1L)).thenReturn(Optional.of(pet));

        Optional<Pet> result = petService.getPetById(1L);

        assertEquals(Optional.of(pet), result);
        verify(iPetRepository, times(1)).findById(1L);
    }

    @Test
    public void test_if_delete_by_id_deletes_pet() {
        Long id = 1L;
        doNothing().when(iPetRepository).deleteById(id);

        petService.deletePetById(id);

        verify(iPetRepository).deleteById(id);
    }

    @Test
    public void test_if_deleteAllPets_deletes() {
        doNothing().when(iPetRepository).deleteAll();

        petService.deleteAllPets();

        verify(iPetRepository).deleteAll();

    }
}

/*    @Test
    public void testAdoptPet_Success() {
        Pet mockPet = new Pet();
        mockPet.setIdPet(1L);
        User mockUser = new User();
        mockUser.setIdUser(1L);

        when(iPetRepository.findById(1L)).thenReturn(Optional.of(mockPet));
        when(userService.findUserById(1L)).thenReturn(mockUser);

        petService.adoptPet(1L, 1L);

        assertEquals(mockUser, mockPet.getUser());
        verify(iPetRepository, times(1)).findById(1L);
        verify(userService, times(1)).findUserById(1L);
        verify(iPetRepository, times(1)).save(mockPet);
    }
}*/




