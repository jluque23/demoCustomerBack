package com.example.demo.Controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Dependant;
import com.example.demo.Service.IDependantService;

public class DependantControllerTest {

    @Mock
    private IDependantService dependantService;

    @InjectMocks
    private DependantController dependantController;

    private Dependant dependant;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        dependant = new Dependant();
        dependant.setAddress("address");
        dependant.setCity("city");
        dependant.setCountry("country");
        dependant.setCreateAt(new Date());
        dependant.setCustomer(new Customer());
        dependant.setFirstName("firstName");
        dependant.setGender("gender");
        dependant.setId(1l);
        dependant.setLastName("lastName");
        dependant.setRelationToCostumer("relationToCostumer");
        dependant.setUpdatedAt(new Date());

    }

    @Test
    void testDelete() {
        dependantController.delete(1l);

        Mockito.verify(dependantService, Mockito.times(1))
        .delete(1l);
    }

    @Test
    void testFindDependantById() {
        Mockito.when(dependantService.findDependantById(1l)).thenReturn(dependant);
        assertNotNull(dependantController.findDependantById(1l));
    }

    @Test
    void testFindDependantByIdNull() {
        Mockito.when(dependantService.findDependantById(1l)).thenReturn(null);
        assertNotNull(dependantController.findDependantById(1l));
    }

    @Test
    void testSaveDependant() {
        Mockito.when(dependantService.save(Mockito.any(Dependant.class))).thenReturn(dependant);
        assertNotNull(dependantController.saveDependant(dependant));
    }

    @Test
    void testUpdate() {
        Mockito.when(dependantService.findDependantById(1l)).thenReturn(dependant);
        Mockito.when(dependantService.save(Mockito.any(Dependant.class))).thenReturn(dependant);
        
        assertNotNull(dependantController.update(dependant,1l));
    }
}
