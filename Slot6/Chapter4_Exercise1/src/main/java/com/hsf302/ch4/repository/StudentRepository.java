package com.hsf302.ch4.repository;

import com.hsf302.ch4.dto.CourseStatDTO;
import com.hsf302.ch4.dto.StudentCreditDTO;
import com.hsf302.ch4.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.hsf302.ch4.pojo.Gender;
import java.time.LocalDate;

import org.springframework.data.repository.query.Param;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    @Query("SELECT s FROM Student s JOIN s.courses c " +
            "WHERE c.code = :code AND s.gpa >= :minGpa ORDER BY s.gpa DESC")
    List<Student> findGoodStudentsInCourse(@Param("code") String courseCode,
                                           @Param("minGpa") double minGpa);

    @Query("SELECT new com.hsf302.ch4.dto.StudentCreditDTO(s.studentCode, s.fullName, COUNT(c), SUM(c.credits)) " +
            "FROM Student s JOIN s.courses c " +
            "GROUP BY s.studentCode, s.fullName " +
            "HAVING SUM(c.credits) >= :minCredits " +
            "ORDER BY SUM(c.credits) DESC, s.fullName")
    List<StudentCreditDTO> getCreditSummary(@Param("minCredits") long minCredits);

    Optional<Student> findByStudentCode(String studentCode);

    boolean existsByEmail(String email);

    long countByActiveTrue();

    List<Student> findByFullNameContainingIgnoreCase(String keyword);

    List<Student> findByEmailEndingWith(String suffix);

    List<Student> findByEmailIsNull();

    List<Student> findByGpaBetweenOrderByGpaDesc(double min, double max);

    List<Student> findByGenderAndActiveTrue(Gender gender);

    List<Student> findByDobAfter(LocalDate date);
    List<Student> findByCourses_CodeOrderByFullNameAsc(String courseCode);
    long countByCourses_Code(String courseCode);
    List<Student> findByCourses_CodeAndActiveTrueOrderByFullNameAsc(String courseCode);

    List<Student> findByCoursesIsEmptyOrderByFullNameAsc();
    boolean existsByStudentCodeAndCourses_Code(String studentCode, String courseCode);
}