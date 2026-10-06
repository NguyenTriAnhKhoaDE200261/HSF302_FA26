package com.hsf302.ch4.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Student> students = new ArrayList<>();

    // Helper method đồng bộ 2 chiều — set cả 2 phía cùng lúc
    public void addStudent(Student s) {
        students.add(s);
        s.setDepartment(this);
    }

    @Override
    public String toString() {
        return "Department{id=" + id + ", code='" + code + "', name='" + name + "'}";
    }
}
