package com.hsf302.ch4.service;

import com.hsf302.ch4.repository.StudentRepository;
import com.hsf302.ch4.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Comparator;
import java.util.List;

import com.hsf302.ch4.pojo.Course;
import com.hsf302.ch4.pojo.Student;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EnrollmentServiceImpl implements EnrollmentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<Course> getCoursesOfStudent(String studentCode) {
        Student s = getStudent(studentCode);
        return s.getCourses().stream()
                .sorted(Comparator.comparing(Course::getCode))
                .toList();
    }

    @Override
    public List<Student> getStudentsOfCourse(String courseCode) {
        Course c = getCourse(courseCode);
        return c.getStudents().stream()
                .sorted(Comparator.comparing(Student::getFullName))
                .toList();
    }

    // ===== Helpers nội bộ trong Service =====
    private Student getStudent(String studentCode) {
        return studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentCode));
    }

    private Course getCourse(String courseCode) {
        return courseRepository.findByCode(courseCode)
                .orElseThrow(() -> new IllegalArgumentException("Course not found: " + courseCode));
    }
}