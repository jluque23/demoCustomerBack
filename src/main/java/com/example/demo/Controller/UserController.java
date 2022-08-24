package com.example.demo.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.User;
import com.example.demo.Service.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
 
    @Autowired
    private IUserService userService;
        
    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAll();
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable(value = "id") Long id) {
        User user = null;

        Map<String,Object> response = new HashMap<>();

        try {
            user = userService.findUserById(id);
        } catch (DataAccessException e){
            response.put("message", "Error with db");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if ( user == null){
            response.put("mensaje", "The ID user: ".concat(id.toString().concat(" Doesn't exist on the database")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<?> saveUser(@Validated @RequestBody User user) {
        User userNew = null;
        Map<String, Object> response = new HashMap<>();

        try {
            userNew = userService.save(user);
        } catch(DataAccessException e){
            response.put("message", "Error inserting to database");
            response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "The user was created succesfully");
        response.put("user", userNew);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id){
        User actualUser = userService.findUserById(id);
        User updatedUser = null;
        Map<String, Object> response = new HashMap<>();

        if(actualUser == null){
            response.put("message", "Error: it can't be edited, The User id: "
					.concat(id.toString().concat(" doesn't exists on the db")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            actualUser.setEmail(user.getEmail());
            actualUser.setFirstName(user.getFirstName());
            actualUser.setLastName(user.getLastName());
            actualUser.setUpdatedAt(new Date());
            updatedUser = userService.save(actualUser);
        } catch(DataAccessException e){
            response.put("message", "Error Updating db");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "The user was updated succesfully");
		response.put("user", updatedUser);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}