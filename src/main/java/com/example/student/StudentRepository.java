package com.example.student;

import com.example.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

  boolean existsByEmail(String email);
}
