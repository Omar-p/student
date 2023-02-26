package com.example.student;

import com.example.student.domain.Gender;
import com.example.student.domain.Student;
import com.example.student.exception.EmailIsAlreadyExistException;
import com.example.student.exception.StudentNotFoundException;
import org.assertj.core.api.BDDAssertions;
import org.assertj.core.api.ThrowableAssertAlternative;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@MockitoSettings
class StudentServiceTest {

  @Mock
  StudentRepository studentRepository;

  @InjectMocks
  StudentService studentServiceUnderTest;

  Student student;

  Student persistedStudent;

  @BeforeEach
  void setUp() {
    student = new Student(null, "name", "name@email.com", Gender.MALE);
    persistedStudent = new Student(1L, "name", "name@email.com", Gender.MALE);

  }

  @Test
  void itShouldAddStudent() {

    when(studentRepository.existsByEmail(student.getEmail())).thenReturn(false);
    when(studentRepository.save(student)).thenReturn(persistedStudent);


    studentServiceUnderTest.addStudent(student);
  }

  @Test
  void addingStudentWithExistingEmail_itShouldThrowEmailIsAlreadyExistException() {
    when(studentRepository.existsByEmail(student.getEmail())).thenReturn(true);

    BDDAssertions.assertThatRuntimeException()
            .isThrownBy(() -> studentServiceUnderTest.addStudent(student))
            .isInstanceOf(EmailIsAlreadyExistException.class)
            .withMessageContaining("is taken");
  }

  @Test
  void whenNoStudentsItShouldReturnEmptyList() {
    when(studentRepository.findAll()).thenReturn(List.of());

    // Then
    BDDAssertions.then(studentServiceUnderTest.getStudents()).isEmpty();
  }

  @Test
  void givenIdForExistingStudentItShouldDeleteTheStudent() {
    when(studentRepository.existsById(1L)).thenReturn(true);

    BDDAssertions.assertThatNoException()
        .isThrownBy(() -> studentServiceUnderTest.deleteStudent(1L));
  }

  @Test
  void givenIdForNonExistingStudentItShouldThrowStudentNotFoundException() {
    when(studentRepository.existsById(1L)).thenReturn(false);

    BDDAssertions.assertThatRuntimeException()
        .isThrownBy(() -> studentServiceUnderTest.deleteStudent(1L))
        .isInstanceOf(StudentNotFoundException.class);
  }
}