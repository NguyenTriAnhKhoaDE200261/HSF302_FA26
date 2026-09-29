package com.hsf302.ch4.runner;

import com.hsf302.ch4.pojo.Department;
import com.hsf302.ch4.pojo.Gender;
import com.hsf302.ch4.pojo.Student;
import com.hsf302.ch4.repository.DepartmentRepository;
import com.hsf302.ch4.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final StudentRepository studentRepository;

    @Override
    public void run(String... args) {
        Department se = new Department();
        se.setCode("SE");
        se.setName("Software Engineering");

        Department ai = new Department();
        ai.setCode("AI");
        ai.setName("Artificial Intelligence");

        Department ia = new Department();
        ia.setCode("IA");
        ia.setName("Information Assurance");

        Department gd = new Department();
        gd.setCode("GD");
        gd.setName("Graphic Design");
        departmentRepository.saveAll(List.of(se, ai, ia, gd));

        List<Student> students = List.of(
                student("SE001", "Nguyen Van An", "an.nv@fpt.edu.vn", Gender.MALE, "2005-03-15", 3.2f, true, se),
                student("SE002", "Tran Thi Binh", "binh.tt@fpt.edu.vn", Gender.FEMALE, "2004-07-22", 3.8f, true, se),
                student("SE003", "Le Van Cuong", "cuong.lv@fpt.edu.vn", Gender.MALE, "2003-11-05", 2.5f, false, se),
                student("AI001", "Pham Thi Dung", "dung.pt@fpt.edu.vn", Gender.FEMALE, "2006-01-10", 3.5f, true, ai),
                student("AI002", "Hoang Van Em", "em.hv@gmail.com", Gender.MALE, "2002-09-30", 2.8f, true, ai),
                student("AI003", "Vo Thi Hoa", "hoa.vt@fpt.edu.vn", Gender.FEMALE, "2005-05-18", 3.9f, true, ai),
                student("IA001", "Dang Van Giang", "giang.dv@gmail.com", Gender.MALE, "2001-12-01", 1.9f, false, ia),
                student("IA002", "Bui Thi Lan", "lan.bt@fpt.edu.vn", Gender.FEMALE, "2004-02-14", 3.1f, true, ia),
                student("SE004", "Nguyen Thi Mai", "mai.nt@fpt.edu.vn", Gender.FEMALE, "2003-08-08", 3.6f, true, se),
                student("IA003", "Do Van Nam", null, Gender.MALE, "2005-10-20", 2.2f, true, ia)
        );
        studentRepository.saveAll(students);

        System.out.println("===== Seed xong: " + departmentRepository.count() + " department, "
                + studentRepository.count() + " student =====");
    }

    private Student student(String code, String name, String email, Gender gender,
                            String dob, float gpa, boolean active, Department dept) {
        Student s = new Student();
        s.setStudentCode(code);
        s.setFullName(name);
        s.setEmail(email);
        s.setGender(gender);
        s.setDob(LocalDate.parse(dob));
        s.setGpa(gpa);
        s.setActive(active);
        dept.addStudent(s);
        return s;
    }
}