package com.tw.apistackbase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeResource{

    private List<Employee> employeeList = new ArrayList<>();

    @GetMapping(path = "/getEmployee", produces = {"application/json"})
    public  ResponseEntity<List<Employee>> getAllEmployee(){
        return ResponseEntity.ok(employeeList);
    }

    @PostMapping(path = "/AddAllEmployee", produces = {"application/json"})
    public ResponseEntity<String> addAllEmployee(@RequestBody List<Employee> employees) {
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
            return ResponseEntity.ok( id + " is not existing.");
        }
    }

    @PutMapping(path = "/change/{id}", produces = {"application/json"})
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {

         employeeList = employeeList.stream()
                .map(objectId -> {
                    if(objectId.getId() == id) {
                       return employee;
                    }
                    return objectId;
                }).collect(Collectors.toList());

        return ResponseEntity.ok("Updated Employee Record " + "\n id: " +  employee.getId() + "\n name: " + employee.getName() + "\n age: " + employee.getAge() + "\n gender: " + employee.getGender());
    }
}
