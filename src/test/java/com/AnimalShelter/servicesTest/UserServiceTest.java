package com.AnimalShelter.servicesTest;

import com.AnimalShelter.models.ERole;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.models.User;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.repositories.IUserRepository;
import com.AnimalShelter.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@SpringBootTest


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private IUserRepository iUserRepository;
    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private UserService userService;
    private User user;
    private Pet pet;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        User userToSave = new User();
        Long idUser = 2L;
        String username = "miangels";
        String email = "ma@gmail.com";
        String password = "123456F5";
        ERole eRole = ERole.USER;
        userToSave.setIdUser(idUser);
        userToSave.setUsername(username);
        userToSave.setEmail(email);
        userToSave.setPassword(password);
        userToSave.setRole(eRole);
        when(iUserRepository.save(userToSave)).thenReturn(userToSave);

        User result = userService.createUser(userToSave);

        assertEquals(2L, result.getIdUser());
        assertEquals("miangels", result.getUsername());
        assertEquals("ma@gmail.com", result.getEmail());
        assertEquals("123456F5", result.getPassword());
        assertEquals(eRole, result.getRole());
        verify(iUserRepository, times(1)).save(userToSave);
    }

    @Test
    public void testGetAllUser() {
        User user = new User();
        user.setIdUser(1L);
        user.setUsername("testuser");
        when(iUserRepository.findAll()).thenReturn(Collections.singletonList(user));

        var result = userService.getAllUser();

        assertEquals(1, result.size());
        assertEquals(user, result.get(0));
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setIdUser(1L);
        user.setUsername("testuser");
        when(iUserRepository.findById(1L)).thenReturn(Optional.of(user));

        var result = userService.getUserById(1L);

        assertEquals(Optional.of(user), result);
    }

    @Test
    public void test_if_deletes_by_id_deletes_user() {
        Long id = 1L;
        doNothing().when(iUserRepository).deleteById(id);

        userService.deleteUserById(id);

        verify(iUserRepository).deleteById(id);
    }

    @Test
    public void test_if_deleteAllUsers_deletes() {
        doNothing().when(iUserRepository).deleteAll();

        userService.deleteAllUsers();

        verify(iUserRepository).deleteAll();

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
