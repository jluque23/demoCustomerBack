package com.example.demo.Service;

import com.example.demo.Model.User;

public interface IUserService {
    public User findByUsername(String username);
}
