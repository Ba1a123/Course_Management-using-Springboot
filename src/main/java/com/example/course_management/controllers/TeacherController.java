package com.example.course_management.controllers;

import com.example.course_management.models.User;
import com.example.course_management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllTeachers() {
        List<User> teachers = userService.getUsersByRole("TEACHER");
        return ResponseEntity.ok(teachers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateTeacher(@PathVariable Long id, @RequestBody User teacherDetails) {
        User updatedTeacher = userService.updateUser(id, teacherDetails, "TEACHER");
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        userService.deleteUser(id, "TEACHER");
        return ResponseEntity.noContent().build();
    }
}
