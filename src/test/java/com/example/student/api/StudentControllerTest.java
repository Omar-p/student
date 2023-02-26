package com.example.student.api;

import com.example.student.StudentRepository;
import com.example.student.domain.Gender;
import com.example.student.domain.Student;
import org.assertj.core.util.TriFunction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ModelResultMatchers;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @MockBean
  StudentRepository studentRepository;

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldReturn200OkAndStudentsList() throws Exception {

    when(studentRepository.findAll()).thenReturn(
        Arrays.asList(
            new Student(1L, "Omar", "omar@email.com", Gender.MALE),
            new Student(2L, "Nada", "nada@email.com", Gender.FEMALE)
        )
    );

    mockMvc.perform(get("/api/v1/students"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$.[0].id").exists())
        .andExpect(jsonPath("$.[0].name").exists())
        .andExpect(jsonPath("$.[0].email").exists())
        .andExpect(jsonPath("$.[0].gender").exists());
  }
}