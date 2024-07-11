package com.saveetha.employeeappdb.employees;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeeData(){
        return employeeRepository.findAll();
    }

    public void addNewEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findEmployeeByEmail(employee.getEmployeeEmail());

        if (existingEmployee.isPresent())
        {
            throw new ResponseStatusException(HttpStatusCode.valueOf(419),"Email already exists");
        }

        employeeRepository.save(employee);
    }



    public Employee getEmployeeDataById(Long employeeID){

        Employee employeeWithId = employeeRepository.findById(employeeID)
                .orElseThrow( () -> new ResponseStatusException(
                        HttpStatusCode.valueOf(405), "Employee with id " + employeeID + " doesn't exist")
                );

        return employeeWithId;
    }

    @Transactional
    public void updateExistingEmployee(Long employeeID, Employee employeeToBeUpdated) {
        Employee matchingEmployee = employeeRepository.findById(employeeID)
                .orElseThrow( () -> new ResponseStatusException(
                        HttpStatusCode.valueOf(405), "Employee with id " + employeeID + " doesn't exist")
                );

        String updatedEmployeeName = employeeToBeUpdated.getEmployeeName();
        String updatedEmployeeEmail = employeeToBeUpdated.getEmployeeEmail();

        if (updatedEmployeeName != null && updatedEmployeeName.length() > 0 && !Objects.equals(matchingEmployee.getEmployeeName(),updatedEmployeeName))
        {
            matchingEmployee.setEmployeeName(updatedEmployeeName);
        }

        if (updatedEmployeeEmail != null && updatedEmployeeEmail.length() > 0 && !Objects.equals(matchingEmployee.getEmployeeEmail(),updatedEmployeeEmail))
        {
            Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(updatedEmployeeEmail);

            if (employeeOptional.isPresent())
            {
                throw new ResponseStatusException(HttpStatusCode.valueOf(419),"There is an existing account with this email.");
            }

            matchingEmployee.setEmployeeEmail(updatedEmployeeEmail);
        }
    }

    public void deleteExistingEmployee(Long employeeID) {
        boolean employeeExists = employeeRepository.existsById(employeeID);

        if (!employeeExists)
        {
            throw new ResponseStatusException(HttpStatusCode.valueOf(405),"Employee with id " + employeeID + " doesn't exist");
        }

        employeeRepository.deleteById(employeeID);
    }
}
