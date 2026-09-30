package com.hsf302.ch4.service;

import com.hsf302.ch4.pojo.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

import com.hsf302.ch4.pojo.Gender;
import java.time.LocalDate;


public interface StudentService {
    List<Student> searchByName(String keyword);

    List<Student> findByEmailDomain(String domain);

    List<Student> findWithoutEmail();
    long count();

    Optional<Student> findById(Long id);

    boolean existsById(Long id);

    List<Student> findAllSortedByGpaDesc();

    Page<Student> findPage(int pageIndex, int size, String sortField);

    Optional<Student> findByStudentCode(String studentCode);

    boolean isEmailExisted(String email);

    long countActive();

    List<Student> findByGpaRange(double min, double max);

    List<Student> findActiveByGender(Gender gender);

    List<Student> findBornAfter(LocalDate date);
}