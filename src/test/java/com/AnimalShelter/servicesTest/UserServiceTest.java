package com.AnimalShelter.servicesTest;

import com.AnimalShelter.models.ERole;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.models.User;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.repositories.IUserRepository;
import com.AnimalShelter.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest

public class UserServiceTest {
    @Mock
    private IUserRepository iUserRepository;
    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private UserService userService;
    private User user;
    private Pet pet;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setIdUser(1L);
        user.setUsername("AlessiaR");
        user.setEmail("aapromero@gmail.com");
        user.setPassword("ale08");
        user.setRole(ERole.valueOf("USER"));
    }

    @Test
    public void testFindUserById() {
        when(iUserRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        User foundUser = userService.findUserById(1L);
        assertEquals(user, foundUser);
    }

    @Test
    public void testUpdateUser() {
        // Given
        User user = new User();
        user.setIdUser(1L);
        user.setUsername("Rombelli");
        user.setEmail("romerobellisamar@gmail.com");
        user.setPassword("Password");
        user.setRole(ERole.valueOf("ADMIN"));

        User updatedUser = new User();
        updatedUser.setIdUser(1L);
        updatedUser.setUsername("Rombelli");
        updatedUser.setEmail("romerobellisamar@gmail.com");
        updatedUser.setPassword("Password24");
        updatedUser.setRole(ERole.valueOf("ADMIN"));

        when(iUserRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(iUserRepository.save(any(User.class))).thenReturn(updatedUser);

        // When
        User result = userService.updateUser(updatedUser);

        // Then
        assertNotNull(result);
        assertEquals(updatedUser.getIdUser(), result.getIdUser());
        assertEquals(updatedUser.getUsername(), result.getUsername());
        assertEquals(updatedUser.getEmail(), result.getEmail());
        assertEquals(updatedUser.getPassword(), result.getPassword());
        assertEquals(updatedUser.getRole(), result.getRole());

        verify(iUserRepository, times(1)).findById(1L);
        verify(iUserRepository, times(1)).save(any(User.class));
    }
}
