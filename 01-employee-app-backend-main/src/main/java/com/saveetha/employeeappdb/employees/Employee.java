package com.saveetha.employeeappdb.employees;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Employee {
    @Id
    @SequenceGenerator(
            name = "employeeSequence",
            sequenceName = "employeeSequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employeeSequence"
    )
    private Long employeeID;
    private String employeeName;
    @Transient
    private Integer employeeAge;
    private LocalDate dateOfBirth;
    private String employeeEmail;

    public Employee() {
    }

    public Employee(String employeeName, LocalDate dateOfBirth, String employeeEmail) {
        this.employeeName = employeeName;
        this.dateOfBirth = dateOfBirth;
        this.employeeEmail = employeeEmail;
    }


    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getEmployeeAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
}