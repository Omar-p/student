package com.example.student.api;

import com.example.student.domain.Gender;
import com.example.student.domain.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Student> students() {
    return Arrays.asList(
        new Student(1L, "Omar", "omar@email.com", Gender.MALE),
        new Student(1L, "Nada", "nada@email.com", Gender.FEMALE)
    );
  }
}
