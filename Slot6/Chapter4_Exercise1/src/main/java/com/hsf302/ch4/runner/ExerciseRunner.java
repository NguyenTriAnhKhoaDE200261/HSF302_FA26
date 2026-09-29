package com.hsf302.ch4.runner;

import com.hsf302.ch4.pojo.Student;
import com.hsf302.ch4.service.DepartmentService;
import com.hsf302.ch4.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order(2)
@RequiredArgsConstructor
public class ExerciseRunner implements CommandLineRunner {

    private final DepartmentService departmentService;
    private final StudentService studentService;

    @Override
    public void run(String... args) {
        System.out.println("\n===== TODO 6: count / findById / existsById =====");
        System.out.println("Tổng số department: " + departmentService.count());
        System.out.println("Tổng số student: " + studentService.count());

        Optional<Student> s1 = studentService.findById(1L);
        System.out.println("Student id=1: " + s1.map(Student::toString).orElse("Không tìm thấy"));

        System.out.println("Student id=999 tồn tại? " + studentService.existsById(999L));
        System.out.println("Department id=1 tồn tại? " + departmentService.existsById(1L));
    }
}