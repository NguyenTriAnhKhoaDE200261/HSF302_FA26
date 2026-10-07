package com.hsf302.chapter6.repository;

import com.hsf302.chapter6.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /** Kiểm tra xem email đã tồn tại chưa (dùng khi thêm mới) */
    boolean existsByEmailIgnoreCase(String email);

    /** Kiểm tra xem email đã được sinh viên KHÁC sử dụng chưa (dùng khi cập nhật) */
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
}