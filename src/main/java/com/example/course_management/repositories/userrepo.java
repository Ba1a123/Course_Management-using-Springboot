package com.example.course_management.repositories;

import com.example.course_management.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userrepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
