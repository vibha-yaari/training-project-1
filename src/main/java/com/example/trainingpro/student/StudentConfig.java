package com.example.trainingpro.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.AUGUST;
import static java.time.Month.FEBRUARY;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepo repo)
    {
        return args -> {
           Student vibha =
                   new Student(122,"Vibha","Vibha2@",
                           LocalDate.of(2001, AUGUST,22)
            );
            Student ankita =
            new Student(132,"Ankita","Ank2@", LocalDate.of(2006, FEBRUARY,22));


            repo.saveAll(
                    List.of(vibha,ankita)
            );
        };
    }
}
