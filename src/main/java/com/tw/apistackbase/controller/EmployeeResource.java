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

}
