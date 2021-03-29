package com.artemzi.repositories;

import com.artemzi.entities.Employee;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @NotNull
    @Override @Modifying
    @Transactional
    <S extends Employee> S save(@NotNull S employee);

    @NotNull
    @Override @Transactional(readOnly = true)
    List<Employee> findAll();

    @NotNull
    @Transactional(readOnly = true)
    Page<Employee> findAll(@NotNull Pageable pageable);

    @Override @Modifying @Transactional
    void deleteInBatch(@NotNull Iterable<Employee> employees);
}
