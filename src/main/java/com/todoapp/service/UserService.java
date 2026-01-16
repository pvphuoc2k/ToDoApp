package com.todoapp.service;

import java.util.List;

import com.todoapp.entity.User;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
}