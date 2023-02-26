package com.example.student;

import com.example.student.domain.Gender;
import com.example.student.domain.Student;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:tc:postgresql:15-alpine:///students"
})
class StudentRepositoryTest {


  @Autowired
  StudentRepository studentRepository;

  @Test
  void shouldReturnTrueWhenEmailExists() {
    String email = "test@gmail.com";
    var s = new Student(null, "Test", email, Gender.MALE);

    studentRepository.save(s);

    BDDAssertions.then(studentRepository.existsByEmail(email)).isTrue();
  }

  @Test
  void shouldReturnFalseWhenEmailDoesNotExist() {
    BDDAssertions.then(studentRepository.existsByEmail("omar@gmail.com")).isFalse();
  }
}