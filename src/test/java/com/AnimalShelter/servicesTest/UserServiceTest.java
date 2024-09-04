package com.AnimalShelter.servicesTest;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
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

}

