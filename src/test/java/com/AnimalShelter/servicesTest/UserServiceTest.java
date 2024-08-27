package com.AnimalShelter.servicesTest;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.models.User;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.repositories.IUserRepository;
import com.AnimalShelter.services.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UserServiceTest {
    @Mock
    private IUserRepository iUserRepository;
    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private UserService userService;
    private User user;
    private Pet pet;

}
