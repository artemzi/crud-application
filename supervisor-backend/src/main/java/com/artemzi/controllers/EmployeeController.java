package com.artemzi.controllers;

import com.artemzi.cores.exceptions.CoreException;
import com.artemzi.cores.rest.request.EmployeeDto;
import com.artemzi.cores.rest.response.EmployeeResponse;
import com.artemzi.entities.Employee;
import com.artemzi.services.EmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * получение всех сотрудников с окладами
     */
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(Pageable pageable) {
        return ResponseEntity.ok(
                employeeService.getAll(pageable)
                        .stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    /**
     * одиночное создание сотрудника с окладом
     */
    @PostMapping("/create")
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeDto employee) {
        return ResponseEntity.ok(this.convertToDto(employeeService.create(employee)));
    }

    /**
     * одиночное изменение оклада сотрудника
     */
    @PutMapping("/update")
    public ResponseEntity<EmployeeResponse> update(@RequestBody EmployeeDto employee) {
        if (employee.getId() == null) {
            throw new CoreException("Id в update не может быть null");
        }
        return ResponseEntity.ok(this.convertToDto(employeeService.update(employee)));
    }

    /**
     * удаление всех сотрудников с окладами
     */
    @DeleteMapping("/clean")
    public ResponseEntity<String> removeAll() {
        employeeService.deleteAll();
        return ResponseEntity.ok("Done");
    }

    // TODO: find better place for method
    public EmployeeResponse convertToDto(Employee employee) {
        return new EmployeeResponse(employee.getId(), employee.getName(), employee.getSalary().getValue());
    }
}
