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

        // ===== TODO 6 =====
        System.out.println("\n===== TODO 6: count / findById / existsById =====");
        System.out.println("Tổng số department: " + departmentService.count());
        System.out.println("Tổng số student: " + studentService.count());

        Optional<Student> s1 = studentService.findById(1L);
        System.out.println("Student id=1: " + s1.map(Student::toString).orElse("Không tìm thấy"));

        System.out.println("Student id=999 tồn tại? " + studentService.existsById(999L));
        System.out.println("Department id=1 tồn tại? " + departmentService.existsById(1L));

        // ===== TODO 7 =====
        System.out.println("\n===== TODO 7: findAll(Sort) / findAll(Pageable) =====");

        System.out.println("-- Sắp xếp theo GPA giảm dần --");
        studentService.findAllSortedByGpaDesc()
                .forEach(s -> System.out.println("  " + s));

        System.out.println("-- Trang 0, size 3, sắp xếp theo fullName --");
        studentService.findPage(0, 3, "fullName")
                .forEach(s -> System.out.println("  " + s));

        // ===== TODO 8 =====
        System.out.println("\n===== TODO 8: findBy / existsBy / countBy =====");

        System.out.println("Tìm student SE002: "
                + studentService.findByStudentCode("SE002").map(Student::toString).orElse("Không tìm thấy"));

        System.out.println("Email 'an.nv@fpt.edu.vn' đã tồn tại? "
                + studentService.isEmailExisted("an.nv@fpt.edu.vn"));
        System.out.println("Email 'khongton@gmail.com' đã tồn tại? "
                + studentService.isEmailExisted("khongton@gmail.com"));

        System.out.println("Số student đang active: " + studentService.countActive());

        System.out.println("\n===== TODO 9: Containing / IgnoreCase / EndingWith / IsNull =====");

        System.out.println("-- Tìm student có tên chứa 'van' (không phân biệt hoa thường) --");
        studentService.searchByName("van")
                .forEach(s -> System.out.println("  " + s));

        System.out.println("-- Student thuộc domain email fpt.edu.vn --");
        studentService.findByEmailDomain("fpt.edu.vn")
                .forEach(s -> System.out.println("  " + s));

        System.out.println("-- Student chưa có email --");
        studentService.findWithoutEmail()
                .forEach(s -> System.out.println("  " + s));
    }
}