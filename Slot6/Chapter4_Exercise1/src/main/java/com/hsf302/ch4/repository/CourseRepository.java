package com.hsf302.ch4.repository;

import com.hsf302.ch4.pojo.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCode(String code);

    List<Course> findBySemesterOrderByCodeAsc(String semester);
    long countBySemester(String semester);

    List<Course> findByStudents_StudentCodeOrderByCodeAsc(String studentCode);
    List<Course> findByStudents_Department_CodeOrderByCodeAsc(String deptCode);
    List<Course> findDistinctByStudents_Department_CodeOrderByCodeAsc(String deptCode);
}