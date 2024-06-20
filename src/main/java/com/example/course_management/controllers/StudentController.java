package com.example.course_management.controllers;

import com.example.course_management.models.User;
import com.example.course_management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllStudents() {
        List<User> students = userService.getUsersByRole("STUDENT");
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateStudent(@PathVariable Long id, @RequestBody User studentDetails) {
        User updatedStudent = userService.updateUser(id, studentDetails, "STUDENT");
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        userService.deleteUser(id, "STUDENT");
        return ResponseEntity.noContent().build();
    }
}
