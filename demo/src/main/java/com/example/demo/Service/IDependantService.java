package com.example.demo.Service;

import com.example.demo.Model.Dependant;

public interface IDependantService {
    public Dependant save(Dependant dependant);

    public Dependant findDependantById(Long id);

    public void delete(Long id);
}
