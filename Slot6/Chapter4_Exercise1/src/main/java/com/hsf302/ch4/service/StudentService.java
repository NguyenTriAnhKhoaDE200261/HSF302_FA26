package com.hsf302.ch4.service;

import com.hsf302.ch4.pojo.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    long count();

    Optional<Student> findById(Long id);

    boolean existsById(Long id);

    List<Student> findAllSortedByGpaDesc();

    Page<Student> findPage(int pageIndex, int size, String sortField);

    Optional<Student> findByStudentCode(String studentCode);

    boolean isEmailExisted(String email);

    long countActive();
}