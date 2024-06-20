package com.example.course_management.services;

import com.example.course_management.models.User;
import com.example.course_management.repositories.userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    public User updateUser(Long id, User userDetails, String role) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getRole().equals(role)) {
            throw new RuntimeException("User role mismatch");
        }
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Long id, String role) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getRole().equals(role)) {
            throw new RuntimeException("User role mismatch");
        }
        userRepository.delete(user);
    }
}
