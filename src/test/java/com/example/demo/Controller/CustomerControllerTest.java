package com.example.demo.Controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.demo.Model.Customer;
import com.example.demo.Service.ICustomerService;

public class CustomerControllerTest {
    @Mock
    private ICustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private Customer customer;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        customer = new Customer();
        customer.setAddress("address");
        customer.setCity("city");
        customer.setCountry("country");
        customer.setCreateAt(new Date());
        customer.setEmail("email");
        customer.setFirstName("firstName");
        customer.setGender("gender");
        customer.setId(1l);
        customer.setLastName("lastName");
        customer.setPhoneNumber("phoneNumber");
        customer.setStatus("status");
        customer.setUpdatedAt(new Date());
    }

    @Test
    void testFindAllCustomers() {
        Mockito.when(customerService.findAll()).thenReturn(Arrays.asList(customer));
        assertNotNull(customerController.findAllCustomers());
    }

    @Test
    void testFindCustomerById() {
        Mockito.when(customerService.findCustomerById(1l)).thenReturn(customer);
        assertNotNull(customerController.findCustomerById(1l));
    }
   
    @Test()
    void testFindCustomerByIdNull() {
        Mockito.when(customerService.findCustomerById(1l)).thenReturn(null);
        assertNotNull(customerController.findCustomerById(1l));
    }

    @Test
    void testSaveCustomer() {
        Mockito.when(customerService.save(Mockito.any(Customer.class))).thenReturn(customer);
        assertNotNull(customerController.saveCustomer(customer));
    }

    @Test
    void testUpdate() {
        Mockito.when(customerService.findCustomerById(1l)).thenReturn(customer);
        Mockito.when(customerService.save(Mockito.any(Customer.class))).thenReturn(customer);
        assertNotNull(customerController.update(customer, 1l));
    }
}
