package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.demo.Model.Customer;
import com.example.demo.Repository.CustomerRepository;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        customer = new Customer();
        customer.setAddress("address");
        customer.setCity("city");
        customer.setCountry("country");
        customer.setCreateAt(new Date());
        customer.setEmail("email");
        customer.setFirstName("firstName");
        customer.setGender("gender");
        customer.setLastName("lastName");
        customer.setId(1l);
        customer.setPhoneNumber("phoneNumber");
        customer.setStatus("status");
        customer.setUpdatedAt(new Date());

    }

    @Test
    void testFindAll() {
        Mockito.when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
        assertNotNull(customerService.findAll());
    }

    @Test
    void testFindCustomerById() {
        Mockito.when((customerRepository.findById(1l))).thenReturn(Optional.of(customer));
        assertNotNull(customerService.findCustomerById(1l));
    }

    @Test
    void testSave() {
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        assertNotNull(customerService.save(customer));
    }
}
