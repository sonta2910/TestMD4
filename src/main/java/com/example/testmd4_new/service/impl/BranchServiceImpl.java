package com.example.testmd4_new.service.impl;

import com.example.testmd4_new.model.Branch;
import com.example.testmd4_new.repository.IBranchRepository;
import com.example.testmd4_new.service.ICoreService;
import org.aspectj.bridge.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl implements ICoreService<Branch> {
    @Autowired
    private IBranchRepository iBranchRepository;

    @Override
    public Page<Branch> findAll(String name, Pageable pageable) {
        return iBranchRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Branch findOne(Long id) {
        return iBranchRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Branch branch) {
iBranchRepository.save(branch);
    }

    @Override
    public void delete(Long id) {
iBranchRepository.deleteById(id);
    }
}
