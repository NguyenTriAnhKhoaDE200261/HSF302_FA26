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
        System.out.println("\n===== TODO 7: findAll(Sort) / findAll(Pageable) =====");

        System.out.println("-- Sắp xếp theo GPA giảm dần --");
        studentService.findAllSortedByGpaDesc()
                .forEach(s -> System.out.println("  " + s));

        System.out.println("-- Trang 0, size 3, sắp xếp theo fullName --");
        studentService.findPage(0, 3, "fullName")
                .forEach(s -> System.out.println("  " + s));
    }
}