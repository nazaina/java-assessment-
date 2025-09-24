package com.example.java.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories(basePackages = "com.example.java.assessment.repository")
public class JavaAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaAssessmentApplication.class, args);
	}

}
