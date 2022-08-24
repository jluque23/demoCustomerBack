package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Dependant;
import com.example.demo.Repository.DependantRepository;

@Service
public class DependantServiceImpl implements IDependantService{

    @Autowired
    private DependantRepository dependantRepository;

    @Override
    public Dependant save(Dependant dependant) {
        return dependantRepository.save(dependant);
    }

    @Override
    public Dependant findDependantById(Long id) {
        return dependantRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        dependantRepository.deleteById(id);
    }
   
}
