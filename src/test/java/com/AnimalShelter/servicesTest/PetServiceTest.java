package com.AnimalShelter.servicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.models.User;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.repositories.IUserRepository;
import com.AnimalShelter.services.PetService;
import com.AnimalShelter.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

public class PetServiceTest {

    @Mock
    private IPetRepository iPetRepository;

    @Mock
    private IUserRepository iUserRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private PetService petService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindPetById_Success() {
        Pet mockPet = new Pet();
        mockPet.setIdPet(1L);
        when(iPetRepository.findById(1L)).thenReturn(Optional.of(mockPet));

        Pet result = petService.findPetById(1L);

        assertEquals(mockPet, result);
        verify(iPetRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindPetById_NotFound() {
        when(iPetRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> petService.findPetById(1L));
        verify(iPetRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdatePet_Success() {
        Pet mockPet = new Pet();
        mockPet.setIdPet(1L);
        when(iPetRepository.findById(1L)).thenReturn(Optional.of(mockPet));
        when(iPetRepository.save(any(Pet.class))).thenReturn(mockPet);

        Pet petToUpdate = new Pet();
        petToUpdate.setIdPet(1L);
        petToUpdate.setName("UpdatedName");

        Pet updatedPet = petService.updatePet(petToUpdate);

        assertEquals("UpdatedName", updatedPet.getName());
        verify(iPetRepository, times(1)).findById(1L);
        verify(iPetRepository, times(1)).save(mockPet);
    }

    @Test
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
}