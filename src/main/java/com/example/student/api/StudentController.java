package com.example.student.api;

import com.example.student.StudentService;
import com.example.student.domain.Student;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }


  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Student> students() {
    return studentService.getStudents();
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public void createNewStudent(@RequestBody @Valid Student student) {
    studentService.addStudent(student);
  }

  @DeleteMapping(path = "{studentId}")
  public void deleteStudent(@PathVariable("studentId") Long studentId) {
    studentService.deleteStudent(studentId);
  }
}
