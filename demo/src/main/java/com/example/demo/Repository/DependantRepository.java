package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Dependant;

@Repository
public interface DependantRepository extends JpaRepository<Dependant,Long>{
    
}
