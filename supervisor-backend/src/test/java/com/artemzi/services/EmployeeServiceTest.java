package com.artemzi.services;

import com.artemzi.entities.Employee;
import com.artemzi.entities.Salary;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    Employee dao;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployeesTest() {
        List<Employee> employeeDtoList = new ArrayList<>();
        Employee employee = new Employee(1L, "Uasya", new Salary(1L, 100500L, null));
        Employee employee2 = new Employee(2L, "Petya", new Salary(1L, 1005010L, null));
        Employee employee3 = new Employee(3L, "John Doe", new Salary(1L, 1005020L, null));

        employeeDtoList.add(employee);
        employeeDtoList.add(employee2);
        employeeDtoList.add(employee3);

        Page<Employee> employees = new PageImpl<>(employeeDtoList);
        when(employeeService.getAll(PageRequest.of(0, 100))).thenReturn(employees);

        Page<Employee> empList = employeeService.getAll(PageRequest.of(0, 100));

        assertEquals(3, empList.getTotalElements());
        verify(employeeService, times(1)).getAll(PageRequest.of(0, 100));
    }
}
