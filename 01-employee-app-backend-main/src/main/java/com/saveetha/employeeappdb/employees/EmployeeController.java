package com.saveetha.employeeappdb.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public List<Employee> getEmployee(){
        return employeeService.getEmployeeData();
    }

    @PostMapping("/")
    public void postEmployee(@RequestBody Employee employee){
        employeeService.addNewEmployee(employee);
    }

    @GetMapping(path = "/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long employeeID){
        return employeeService.getEmployeeDataById(employeeID);
    }

    @PutMapping(path = {"/{id}"})
    public void putEmployee(@PathVariable("id") Long employeeID,
                    @RequestBody Employee employeeFromUser)
    {
        employeeService.updateExistingEmployee(employeeID, employeeFromUser);
    }


    @DeleteMapping(path = {"/{id}"})
    public void deleteEmployee(@PathVariable("id") Long employeeID)
    {
        employeeService.deleteExistingEmployee(employeeID);
    }
}
