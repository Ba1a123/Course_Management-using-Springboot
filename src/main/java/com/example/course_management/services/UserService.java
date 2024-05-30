package com.example.course_management.services;

import com.example.course_management.models.User;
import com.example.course_management.repositories.userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private userrepo userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean authenticateUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
           
            return passwordEncoder.matches(user.getPassword(), existingUser.getPassword());
        }
        return false; // User not found or password doesn't match
    }

}
