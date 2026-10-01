package com.hsf302.ch4.runner;

import com.hsf302.ch4.pojo.Course;
import com.hsf302.ch4.pojo.Student;
import com.hsf302.ch4.repository.CourseRepository;
import com.hsf302.ch4.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Order(2)
@RequiredArgsConstructor
public class CourseDataInitializer implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public void run(String... args) {
        Course prj301 = course("PRJ301", "Java Web Application Development", 3, 5, "FA26");
        Course hsf302 = course("HSF302", "Hibernate & Spring Framework", 3, 6, "FA26");
        Course swp391 = course("SWP391", "Software Development Project", 4, 4, "FA26");
        Course ail303 = course("AIL303", "Machine Learning", 3, 4, "FA26");
        Course iaa202 = course("IAA202", "Risk Management in Information Systems", 3, 4, "SU26");
        Course mkt101 = course("MKT101", "Marketing Principles", 2, 4, "SU26");

        courseRepository.saveAll(List.of(prj301, hsf302, swp391, ail303, iaa202, mkt101));

        enroll("SE001", prj301, hsf302, swp391);
        enroll("SE002", prj301, hsf302, ail303);
        enroll("SE003", prj301);
        enroll("SE004", hsf302, swp391);
        enroll("AI001", ail303, hsf302);
        enroll("AI002", ail303);
        enroll("AI003", ail303, prj301, swp391);
        enroll("IA001", iaa202);
        enroll("IA002", iaa202, hsf302);
        // IA003: chưa đăng ký khóa học nào

        System.out.println("===== Seed xong: " + courseRepository.count() + " course =====");
    }

    private Course course(String code, String name, int credits, int capacity, String semester) {
        Course c = new Course();
        c.setCode(code);
        c.setName(name);
        c.setCredits(credits);
        c.setCapacity(capacity);
        c.setSemester(semester);
        return c;
    }

    private void enroll(String studentCode, Course... courses) {
        Student s = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new IllegalStateException("Không tìm thấy student " + studentCode));
        for (Course c : courses) {
            s.enroll(c);
        }
        studentRepository.save(s);
    }
}