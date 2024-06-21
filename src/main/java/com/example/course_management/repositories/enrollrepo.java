package com.example.course_management.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.course_management.models.User;
import com.example.course_management.models.Course;
import com.example.course_management.models.Enrollment;
import java.util.*;

@Repository
public interface enrollrepo extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);
    Enrollment findByCourseAndStudent(Course course, User student);
}
