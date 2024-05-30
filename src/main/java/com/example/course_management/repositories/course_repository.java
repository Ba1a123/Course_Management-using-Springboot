package com.example.course_management.repositories;

import com.example.course_management.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface course_repository extends JpaRepository<Course, Long> {
}
