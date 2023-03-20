package com.example.testmd4_new.repository;

import com.example.testmd4_new.model.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBranchRepository extends JpaRepository<Branch,Long> {
    Page<Branch> findAllByNameContaining(String name, Pageable pageable);
}
