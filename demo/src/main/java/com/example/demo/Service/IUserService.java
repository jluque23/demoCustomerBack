package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.User;

public interface IUserService {
    public List<User> findAll();

    public User save(User user);

    public User findUserById(Long id);
}
