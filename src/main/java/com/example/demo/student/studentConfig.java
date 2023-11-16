package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class studentConfig {

    @Bean
    CommandLineRunner commandLineRunner(studentRepository repository){
        return args ->{
           student Mohammed = new student(

                    "Mohammed",
                    "mohammedelkasri@gmail.com",
                    LocalDate.of(2001, Month.JUNE,2)
            );
            student Hamza = new student(

                    "Hamza",
                    "Hamza@gmail.com",
                    LocalDate.of(2003, Month.JUNE,2)
            );

           repository.saveAll(
                   List.of(Mohammed, Hamza)
                        );
        };
    }
}
