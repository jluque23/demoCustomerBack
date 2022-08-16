package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.Customer;

public interface ICustomerService {
    public List<Customer> findAll();

    public Customer save(Customer customer);

    public Customer findCustomerById(Long id);
}
