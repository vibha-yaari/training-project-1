package com.example.trainingpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TrainingProApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingProApplication.class, args);
	}

}
