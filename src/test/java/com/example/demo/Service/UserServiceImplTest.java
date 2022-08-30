package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    User user = new User();
    Role role = new Role();
    
        
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        role.setId(1l);
        role.setName("ROLE_USER");
        user.setEmail("email");
        user.setEnabled(true);
        user.setFirstName("firstName");
        user.setId(1l);
        user.setLastName("lastName");
        user.setPassword("password");
        user.setRoles(Arrays.asList(role));
        user.setUsername("username");


    }

    @Test
    void testFindByUsername() {
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        assertNotNull(userServiceImpl.findByUsername(user.getUsername()));
    }

    @Test
    void testLoadUserByUsername() {
        
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        assertNotNull(userServiceImpl.loadUserByUsername(user.getUsername()));
    }
}
