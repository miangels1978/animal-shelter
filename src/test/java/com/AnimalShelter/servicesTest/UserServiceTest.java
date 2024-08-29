package com.AnimalShelter.servicesTest;

import com.AnimalShelter.models.Pet;
import com.AnimalShelter.models.User;
import com.AnimalShelter.repositories.IPetRepository;
import com.AnimalShelter.repositories.IUserRepository;
import com.AnimalShelter.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

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

    @Test
    public void test_if_deletes_by_id_deletes_user(){
        Long id = 1L;
        doNothing().when(iUserRepository).deleteById(id);

        userService.deleteUserById(id);

        verify(iUserRepository).deleteById(id);
    }
    @Test
    public void test_if_deleteAllUsers_deletes(){

        // user1 = new User();
        //Donation donation1 = new Donation(1L, "pepita", 123.2, user1);
        //Donation donation2 = new Donation(1L, "pepita", 123.2, user1);
        //List<Donation> donationList = new ArrayList<>(Arrays.asList(donation1, donation2));
        doNothing().when(iUserRepository).deleteAll();

        userService.deleteAllUsers();

        verify(iUserRepository).deleteAll();

    }
}
