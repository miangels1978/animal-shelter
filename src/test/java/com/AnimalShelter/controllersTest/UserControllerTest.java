package com.AnimalShelter.controllersTest;

import com.AnimalShelter.controllers.UserController;
import com.AnimalShelter.models.User;
import com.AnimalShelter.repositories.IUserRepository;
import com.AnimalShelter.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)

public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetAllUser() {

        User user1 = new User();
        user1.setIdUser(1L);
        user1.setUsername("John");

        User user2 = new User();
        user2.setIdUser(2L);
        user2.setUsername("Jane");

        List<User> userList = Arrays.asList(user1, user2);

        when(IUserRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUser();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getUsername());
        assertEquals("Jane", result.get(1).getUsername());
    }
    @Test
    public void testGetUserById() {

        long id = 1L;
        User user = new User();
        user.setIdUser(id);
        user.setUsername("John");

        when(IUserRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(id);

        assertEquals(true, result.isPresent());
        assertEquals("John", result.get().getUsername());
    }
}


