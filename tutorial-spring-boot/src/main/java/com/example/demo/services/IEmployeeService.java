package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Dto.EmployeeDto;

public interface IEmployeeService
{
    List<EmployeeDto> getAllEmployees();
    EmployeeDto createEmployee(EmployeeDto employee);
    Optional<EmployeeDto> findEmployeeById(Long employeeId );
    EmployeeDto updateEmployee(EmployeeDto employee);
    boolean deleteEmployee(EmployeeDto employee);
}
