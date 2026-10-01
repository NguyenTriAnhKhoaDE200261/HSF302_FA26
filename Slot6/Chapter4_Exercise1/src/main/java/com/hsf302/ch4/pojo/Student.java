package com.hsf302.ch4.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_code", nullable = false, unique = true, length = 20)
    private String studentCode;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    private LocalDate dob;

    private float gpa;

    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    // Helper đồng bộ 2 chiều — chỉ owning side mới quyết định bảng trung gian
    public void enroll(Course course) {
        courses.add(course);
        course.getStudents().add(this);
    }

    public void unenroll(Course course) {
        courses.remove(course);
        course.getStudents().remove(this);
    }

    // equals/hashCode theo business key "studentCode"
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Objects.equals(studentCode, student.studentCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentCode);
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", code='" + studentCode + "', name='" + fullName +
                "', gpa=" + gpa + ", active=" + active + "}";
    }
}