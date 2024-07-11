package com.saveetha.employeeappdb.employees;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository)
    {
        return args -> {
            Employee abc = new Employee(
                    "Abc",
                    LocalDate.of(2000, Month.JANUARY,2),
                    "abc@company.com"
            );

            Employee qwer = new Employee(
                    "Qwerty",
                    LocalDate.of(2001, Month.MAY,21),
                    "qwer@company.com"
            );

            Employee john = new Employee(
                    "John",
                    LocalDate.of(1990, Month.MAY,21),
                    "john@company.com"
            );

            Employee jack = new Employee(
                    "Jack",
                    LocalDate.of(1985, Month.MAY,21),
                    "jack@company.com"
            );

            employeeRepository.saveAll(
                    List.of(abc, qwer, john, jack)
            );
        };
    }
}
