package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    User user = new User();
    
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByUsername() {
        
    }

    @Test
    void testLoadUserByUsername() {
        Mockito.when(userRepository.findByUsername("username")).thenReturn(user);
        assertNotNull(userServiceImpl.findByUsername("username"));
    }
}
