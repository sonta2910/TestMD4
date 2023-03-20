package com.example.testmd4_new.controller;

import com.example.testmd4_new.model.Branch;
import com.example.testmd4_new.model.Employee;
import com.example.testmd4_new.service.impl.BranchServiceImpl;
import com.example.testmd4_new.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private BranchServiceImpl branchService;

    @GetMapping
    public ResponseEntity<Page<Employee>> findAll(@PageableDefault Pageable pageable,
                                                  @RequestParam("search") Optional<String> name) {
        Page<Employee> employees;
        if (name.isPresent()) {
            employees = employeeService.findAll(name.get(), pageable);
        } else {
            employees = employeeService.findAll("", pageable);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Employee employee) {
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/branches")
    public ResponseEntity<Page<Branch>> findAllBranch(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(branchService.findAll("", pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.findOne(id), HttpStatus.OK);
    }
}
