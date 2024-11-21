package com.careconnect.careconnect.service;

import java.util.List;

import com.careconnect.careconnect.models.User;

public interface UserService {
    User save(User user);
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
    void deleteById(Long id);
}