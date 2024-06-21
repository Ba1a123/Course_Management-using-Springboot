package com.example.course_management.services;

import com.example.course_management.models.Course;
import com.example.course_management.models.Enrollment;
import com.example.course_management.models.User;
import com.example.course_management.repositories.course_repository;
import com.example.course_management.repositories.enrollrepo;
import com.example.course_management.repositories.userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Courseservice {

    @Autowired
    private course_repository courseRepository;

    @Autowired
    private userrepo userRepository;

    @Autowired
    private enrollrepo enrollmentRepository;

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course courseDetails) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setTitle(courseDetails.getTitle());
        course.setDescription(courseDetails.getDescription());
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        courseRepository.delete(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public boolean addStudentToCourse(Long courseId, Long studentId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        Optional<User> studentOpt = userRepository.findById(studentId);
        if (courseOpt.isPresent() && studentOpt.isPresent()) {
            Course course = courseOpt.get();
            User student = studentOpt.get();
            Enrollment enrollment = new Enrollment();
            enrollment.setCourse(course);
            enrollment.setStudent(student);
            enrollmentRepository.save(enrollment);
            return true;
        }
        return false;
    }

    public boolean removeStudentFromCourse(Long courseId, Long studentId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        Optional<User> studentOpt = userRepository.findById(studentId);
        if (courseOpt.isPresent() && studentOpt.isPresent()) {
            Course course = courseOpt.get();
            User student = studentOpt.get();
            Enrollment enrollment = enrollmentRepository.findByCourseAndStudent(course, student);
            if (enrollment != null) {
                enrollmentRepository.delete(enrollment);
                return true;
            }
        }
        return false;
    }

    public boolean allocateTeacherToCourse(Long courseId, Long teacherId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        Optional<User> teacherOpt = userRepository.findById(teacherId);
        if (courseOpt.isPresent() && teacherOpt.isPresent()) {
            Course course = courseOpt.get();
            User teacher = teacherOpt.get();
            course.setTeacher(teacher);
            courseRepository.save(course);
            return true;
        }
        return false;
    }
}
