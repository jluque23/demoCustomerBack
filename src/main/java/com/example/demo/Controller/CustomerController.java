package com.example.demo.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Customer;
import com.example.demo.Service.ICustomerService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public List<Customer> findAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable(value = "id") Long id) {
        Customer customer = null;
        Map<String, Object> response = new HashMap<>();

        try {
            customer = customerService.findCustomerById(id);
        } catch (DataAccessException e) {
            response.put("message", "Error with db");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (customer == null) {
            response.put("message", "The ID customer: ".concat(id.toString().concat(" Doesn't exist on the database")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<?> saveCustomer(@Validated @RequestBody Customer customer) {
        Customer customerNew = null;
        Map<String, Object> response = new HashMap<>();

        try {
            customerNew = customerService.save(customer);
        } catch (DataAccessException e) {
            response.put("message", "Error inserting to database");
            response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "The customer was created succesfully");
        response.put("customer", customerNew);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Customer customer, @PathVariable Long id) {
        Customer actualCustomer = customerService.findCustomerById(id);
        Customer updatedCustomer = null;
        Map<String, Object> response = new HashMap<>();

        if (actualCustomer == null) {
            response.put("message", "Error: it can't be edited, The Customer id: "
                    .concat(id.toString().concat(" doesn't exists on the db")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            actualCustomer.setAddress(customer.getAddress());
            actualCustomer.setCity(customer.getCity());
            actualCustomer.setCountry(customer.getCountry());
            actualCustomer.setEmail(customer.getEmail());
            actualCustomer.setFirstName(customer.getFirstName());
            actualCustomer.setGender(customer.getGender());
            actualCustomer.setLastName(customer.getLastName());
            actualCustomer.setPhoneNumber(customer.getPhoneNumber());
            actualCustomer.setStatus(customer.getStatus());
            actualCustomer.setUpdatedAt(new Date());

            updatedCustomer = customerService.save(actualCustomer);
        } catch (DataAccessException e) {
            response.put("message", "Error Updating db");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "The customer was updated succesfully");
        response.put("customer", updatedCustomer);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
