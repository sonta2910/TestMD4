package com.example.testmd4_new.repository;

import com.example.testmd4_new.model.Branch;
import com.example.testmd4_new.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IEmployeeRepository extends JpaRepository<Employee,Long> {
    Page<Employee> findAllByNameContaining(String name, Pageable pageable);
}
