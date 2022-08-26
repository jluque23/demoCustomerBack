package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Dependant;
import com.example.demo.Repository.DependantRepository;

public class DependantServiceImplTest {
    @Mock
    public DependantRepository dependantRepository;

    @InjectMocks
    DependantServiceImpl dependantService;

    Dependant dependant;
    
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
        dependantService.delete(1l);

        Mockito.verify(dependantRepository, Mockito.times(1))
                .deleteById(1l);
    }

    @Test
    void testFindDependantById() {
        Mockito.when(dependantRepository.findById(1l)).thenReturn(Optional.of(dependant));
        assertNotNull(dependantService.findDependantById(1l));
    }

    @Test
    void testSave() {
        Mockito.when(dependantRepository.save(dependant)).thenReturn(dependant);
        assertNotNull(dependantService.save(dependant));
    }
}
