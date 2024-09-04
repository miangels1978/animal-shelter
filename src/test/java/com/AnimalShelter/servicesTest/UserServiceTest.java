package com.AnimalShelter.servicesTest;

import com.AnimalShelter.models.Donation;
import com.AnimalShelter.models.ERole;
import com.AnimalShelter.models.Pet;
import com.AnimalShelter.models.User;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.repositories.IUserRepository;
import com.AnimalShelter.services.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;


import static com.AnimalShelter.models.ERole.ADMIN;
import static com.AnimalShelter.models.ERole.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import static org.mockito.Mockito.when;

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
    public void testCreateUser() {

        User userToSave = new User();
        Long idUser = 2L;
        String username = "miangels";
        String email = "ma@gmail.com";
        String password = "123456F5";
        ERole eRole = ERole.USER;



        userToSave.setIdUser(idUser);
        userToSave.setUsername (username);
        userToSave.setEmail (email);
        userToSave.setPassword (password);
        userToSave.setRole(eRole);

        when(iUserRepository.save(userToSave)).thenReturn(userToSave);

        User result = userService.createUser(userToSave);

        assertEquals(2L, result.getIdUser ());
        assertEquals("miangels", result.getUsername ());
        assertEquals("ma@gmail.com", result.getEmail ());
        assertEquals ("123456F5", result.getPassword ());
        assertEquals (eRole, result.getRole());

        verify(iUserRepository, times(1)).save(userToSave);
    }
}

