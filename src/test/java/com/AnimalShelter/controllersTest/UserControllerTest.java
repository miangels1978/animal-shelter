package com.AnimalShelter.controllersTest;

import com.AnimalShelter.controllers.DonationController;
import com.AnimalShelter.controllers.UserController;
import com.AnimalShelter.models.Donation;
import com.AnimalShelter.models.User;
import com.AnimalShelter.services.DonationService;
import com.AnimalShelter.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    public void test_if_deleteByID_deletes_user() throws Exception {
        when(userService.findById(1L)).thenReturn(Optional.of(new User()));

        mockMvc.perform(delete("/api/v1/users/user/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Donation deleted successfully"));

        verify(userService).deleteUserById(1L);
    }
    @Test
    public void test_if_deleteByID_deletes_all_users() throws Exception{
            doNothing().when(userService).deleteAllUsers();

            mockMvc.perform(delete("/api/v1/users"))
                    .andExpect(status().isOk());

            verify(userService).deleteAllUsers();
        }
    }
    }

}
