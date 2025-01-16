package com.example.pizzeria.service;

import com.example.pizzeria.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);

    void signUp(User user);

    void addUser(String username, String password, String confirmPassword, String role, String firstName, String lastName, String email, String phoneNumber);
}
