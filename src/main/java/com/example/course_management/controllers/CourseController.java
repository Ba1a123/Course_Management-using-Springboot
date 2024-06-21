package com.example.course_management.controllers;

import com.example.course_management.models.Course;
import com.example.course_management.services.Courseservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private Courseservice courseService;

    // Endpoint to add a course (Admin)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    // Endpoint to update a course (Admin)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        return courseService.updateCourse(id, courseDetails);
    }

    // Endpoint to delete a course (Admin)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    // Endpoint to get all courses (Admin, Teacher, Student)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Endpoint to add a student to a course (Admin, Teacher)
    @PutMapping("/students/{courseId}/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public String addStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        boolean success = courseService.addStudentToCourse(courseId, studentId);
        return success ? "Student added to course successfully" : "Failed to add student to course";
    }

    // Endpoint to remove a student from a course (Admin, Teacher)
    @DeleteMapping("/students/{courseId}/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public String removeStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        boolean success = courseService.removeStudentFromCourse(courseId, studentId);
        return success ? "Student removed from course successfully" : "Failed to remove student from course";
    }

    // Endpoint to allocate a teacher to a course (Admin)
    @PutMapping("/teachers/{courseId}/{teacherId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String allocateTeacherToCourse(@PathVariable Long courseId, @PathVariable Long teacherId) {
        boolean success = courseService.allocateTeacherToCourse(courseId, teacherId);
        return success ? "Teacher allocated to course successfully" : "Failed to allocate teacher to course";
    }
}
