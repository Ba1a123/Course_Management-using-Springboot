package com.example.course_management.repositories;

import com.example.course_management.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface userrepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    // User findByUsername(String username);
    List<User> findByRole(String role);

}
