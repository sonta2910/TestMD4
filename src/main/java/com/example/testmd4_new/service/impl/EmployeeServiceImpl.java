package com.example.testmd4_new.service.impl;

import com.example.testmd4_new.model.Employee;
import com.example.testmd4_new.repository.IEmployeeRepository;
import com.example.testmd4_new.service.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements ICoreService<Employee> {
    @Autowired
    private IEmployeeRepository iEmployeeRepository;


    @Override
    public Page<Employee> findAll(String name, Pageable pageable) {
        return iEmployeeRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Employee findOne(Long id) {
        return iEmployeeRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Employee employee) {
        iEmployeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        iEmployeeRepository.deleteById(id);
    }
}
