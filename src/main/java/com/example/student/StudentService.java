package com.example.student;

import com.example.student.domain.Student;
import com.example.student.exception.EmailIsAlreadyExistException;
import com.example.student.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public void addStudent(Student student) {

    if (studentRepository.existsByEmail(student.getEmail()))
      throw new EmailIsAlreadyExistException(student.getEmail() + " is taken.");

    studentRepository.save(student);

  }

  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public void deleteStudent(Long studentId) {
    boolean exists = studentRepository.existsById(studentId);
    if (!exists) {
      throw new StudentNotFoundException("Student with id " + studentId + " does not exists");
    }
    studentRepository.deleteById(studentId);
  }
}
