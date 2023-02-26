package com.example.student;

import com.example.student.domain.Gender;
import com.example.student.domain.Student;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}


	@Bean
	public CommandLineRunner dataLoader(StudentRepository studentRepository) {
		return args -> {
			Faker faker = new Faker();
			List<Student> students = new ArrayList<>();
			for (int i = 0; i < 1000; i++) {
				students.add(
						new Student(
							null,
							faker.name().name(),
							faker.internet().emailAddress(),
							new Random().nextInt() % 2 == 0 ? Gender.MALE : Gender.FEMALE
						)
				);
			}
			studentRepository.saveAll(students);
		};
	}
}


