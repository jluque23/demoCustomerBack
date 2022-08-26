package com.example.demo.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Dependant;
import com.example.demo.Service.IDependantService;

@RestController
@RequestMapping("/api/dependant")
@CrossOrigin
public class DependantController {

    @Autowired
    private IDependantService dependantService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findDependantById(@PathVariable(value = "id") Long id) {
        Dependant dependant = null;
        Map<String, Object> response = new HashMap<>();

        try {
            dependant = dependantService.findDependantById(id);
        } catch (DataAccessException e) {
            response.put("message", "Error with db");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (dependant == null) {
            response.put("message",
                    "The ID dependant: ".concat(id.toString().concat(" Doesn't exist on the database")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Dependant>(dependant, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<?> saveDependant(@Validated @RequestBody Dependant dependant) {
        Dependant dependantNew = null;
        Map<String, Object> response = new HashMap<>();

        try {
            dependantNew = dependantService.save(dependant);

        } catch (DataAccessException e) {
            response.put("message", "Error inserting to database");
            response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "The Dependant was created succesfully");
        response.put("dependant", dependantNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Dependant dependant, @PathVariable Long id) {
        Dependant actualDependant = dependantService.findDependantById(id);
        Dependant updatedDependant = null;

        Map<String, Object> response = new HashMap<>();

        if (actualDependant == null) {
            response.put("message", "Error: it can't be edited, The Dependant id: "
                    .concat(id.toString().concat(" doesn't exists on the db")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            actualDependant.setAddress(dependant.getAddress());
            actualDependant.setCity(dependant.getCity());
            actualDependant.setCountry(dependant.getCountry());
            actualDependant.setFirstName(dependant.getFirstName());
            actualDependant.setGender(dependant.getGender());
            actualDependant.setLastName(dependant.getLastName());
            actualDependant.setRelationToCostumer(dependant.getRelationToCostumer());
            actualDependant.setCustomer(dependant.getCustomer());
            actualDependant.setUpdatedAt(new Date());

            updatedDependant = dependantService.save(actualDependant);
        } catch (DataAccessException e) {
            response.put("message", "Error Updating db");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "The Dependant was updated succesfully");
        response.put("dependant", updatedDependant);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            dependantService.delete(id);
        } catch (DataAccessException e) {
            response.put("message", "Error deleting dependant on database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "The dependant has been deleted succesfully!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
