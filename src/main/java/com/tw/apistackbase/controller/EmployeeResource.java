package com.tw.apistackbase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeResource{

    private List<Employee> employeeList = new ArrayList<>();

    @GetMapping(path = "/getEmployee", produces = {"application/json"})
    public  ResponseEntity<List<Employee>> getAllEmployee(){
        return ResponseEntity.ok(employeeList);
    }

    @PostMapping(path = "/AddAllEmployee", produces = {"application/json"})
    public ResponseEntity<String> addAllEmployee(
            @RequestBody List<Employee> employees) {
        employeeList.addAll(employees);
        return ResponseEntity.ok("Added all employees");
    }

    @DeleteMapping(path = "/remove/{id}", produces = {"application/json"})
    public ResponseEntity<String> removeEmployee(@PathVariable int id) {

        Employee removeEmployee = employeeList.stream()
                .filter(employee -> id == employee.getId()).findFirst().orElse(null);
        Boolean isDeleted = employeeList.remove(removeEmployee);

        if ( isDeleted) {
            return ResponseEntity.ok("Deleted Employee Record: " + removeEmployee.getName());
        } else {
            return ResponseEntity.ok("Can't delete" + id);
        }
    }
}
