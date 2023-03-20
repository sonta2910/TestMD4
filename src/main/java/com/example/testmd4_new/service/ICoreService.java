package com.example.testmd4_new.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICoreService<E> {
    Page<E>findAll(String name, Pageable pageable);
    E findOne(Long id);
    void save(E e);
    void delete(Long id);
}
