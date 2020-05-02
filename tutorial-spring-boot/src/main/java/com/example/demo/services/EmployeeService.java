package com.example.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.Dto.EmployeeDto;
import com.example.demo.repositories.IEmployeeRepository;


import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
	public class EmployeeService implements IEmployeeService 
	{
	    @Autowired
	    private IEmployeeRepository employeeRepository;
	    @Autowired
	    private ModelMapper modelMapper;

	    @Override
	    public List<EmployeeDto> getAllEmployees()
	    {
	    	List<EmployeeDto> listEmployees = new ArrayList<EmployeeDto>();
	    	List<Employee> employees = Lists.newArrayList(employeeRepository.findAll());
	    	if ( ! employees.isEmpty() )
	        {
	        	listEmployees = ObjectMapperUtils.mapAll(employees, EmployeeDto.class);
	        }
	        return listEmployees;
	    }

	    @Override
	    public EmployeeDto createEmployee(EmployeeDto employee)
	    {
	      	       
	       Employee employeeEntity = modelMapper.map(employee,Employee.class);
	       Employee newEmployee = employeeRepository.save(employeeEntity);
	       EmployeeDto newEmployeeDTo = modelMapper.map(newEmployee,EmployeeDto.class);
	        return newEmployeeDTo;
	    }

	    @Override
	    public Optional<EmployeeDto> findEmployeeById(Long employeeId)
	    {
	    	EmployeeDto employeeDto = null;
	    	Optional<Employee> employee = employeeRepository.findById(employeeId);
	    	if (null != employee.get())
	    	{
	    	Employee getEmployee = employee.get();
	        employeeDto = modelMapper.map(getEmployee,EmployeeDto.class);
	    	}
	        return Optional.of(employeeDto);
	    }

	    @Override
	    public EmployeeDto updateEmployee(EmployeeDto employee)
	    {
	        Employee employeeEntity = modelMapper.map(employee,Employee.class);
	        Employee employeeUpdate = employeeRepository.save(employeeEntity);
	        EmployeeDto employeeUpdateDto = modelMapper.map(employeeUpdate,EmployeeDto.class);
	        return employeeUpdateDto;
	    }

	    @Override
	    public boolean deleteEmployee(EmployeeDto employee)
	    {
	        Employee employeeEntity = modelMapper.map(employee,Employee.class);
	        employeeRepository.delete(employeeEntity);
	        Employee employeeDeleted = employeeRepository.findById(employee.getId()).get();
	        return (null == employeeDeleted)?true:false;
	    }
	    
	    
	}



