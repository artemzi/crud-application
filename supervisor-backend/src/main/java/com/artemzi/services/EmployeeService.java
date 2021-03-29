package com.artemzi.services;

import com.artemzi.cores.exceptions.RestException;
import com.artemzi.cores.rest.request.EmployeeDto;
import com.artemzi.entities.Employee;
import com.artemzi.entities.Salary;
import com.artemzi.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee create(EmployeeDto employeeDto) {
        // TODO добавить валидацию и убрать треш :)
        Salary salary = new Salary();
        salary.setValue(employeeDto.getSalary());

        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setSalary(salary);

        return employeeRepository.save(employee);
    }

    public Employee update(EmployeeDto employeeDto) {
        Optional<Employee> existed = employeeRepository.findById(employeeDto.getId());
        if (existed.isEmpty()) {
            throw new RestException("Сотрудник не найден");
        }

        // разрешаем менять только salary
        if (employeeDto.getSalary() != null) existed.get().getSalary().setValue(employeeDto.getSalary());

        return employeeRepository.save(existed.get());
    }

    public Page<Employee> getAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public void deleteAll() {
        employeeRepository.deleteAll();
    }
}
