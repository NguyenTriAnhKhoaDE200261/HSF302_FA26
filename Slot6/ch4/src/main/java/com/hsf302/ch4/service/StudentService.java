package com.hsf302.ch4.service;

import java.util.Optional;
import com.hsf302.ch4.pojo.Student;

public interface StudentService {

    long count();

    Optional<Student> findById(Long id);
}