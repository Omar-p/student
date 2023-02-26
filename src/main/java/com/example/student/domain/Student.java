package com.example.student.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

  @Id
  @SequenceGenerator(
      name = "student_sequence",
      sequenceName = "student_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      generator = "student_sequence",
      strategy = GenerationType.SEQUENCE
  )
  private Long id;

  @NotBlank
  @Column(nullable = false)
  private String name;

  @Email
  @NotBlank
  @Column(nullable = false, unique = true)
  private String email;

  @Enumerated(EnumType.STRING)
  @NotBlank
  @Column(nullable = false)
  private Gender gender;
}
