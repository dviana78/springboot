package com.example.demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Dto.EmployeeDto;
import com.example.demo.services.EmployeeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.swagger.annotations.Api;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/employees", produces = "application/json")
@Api(value = "Employee Resource REST Endpoint", description = "Shows the employee info",tags={"Employees"})

public class EmployeeController
{
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService)
    {
        this.employeeService = employeeService;
    }

    @io.swagger.annotations.ApiOperation(value = "View a list of available employees")
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully retrieved employee list"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "There is not employees")
    })
    @GetMapping(path="/")
    public ResponseEntity<List<EmployeeDto>> employees()
    {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        if ( ! employees.isEmpty())
        {
            return new ResponseEntity<List<EmployeeDto>>(employeeService.getAllEmployees(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @io.swagger.annotations.ApiOperation(value = "Get employee by id")
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully retrieved employee"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Employee not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> employeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException
    {
        Optional<EmployeeDto> employee = employeeService.findEmployeeById(employeeId);
        employee.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + employeeId));
        return ResponseEntity.ok().body(employee.get());
    }
    @io.swagger.annotations.ApiOperation(value = "Create a new employee")
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully create a new employee"),
            @io.swagger.annotations.ApiResponse(code = 204, message = "No create employee"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
     })
    @PostMapping(path="/")
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employee)
    {
        EmployeeDto newEmployee = employeeService.createEmployee(employee);
        if ( null != employee)
        {
             return ResponseEntity.ok(newEmployee);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @io.swagger.annotations.ApiOperation(value = "Update a employee")
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully update employee"),
            @io.swagger.annotations.ApiResponse(code = 204, message = "No update employee"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                      @Valid @RequestBody EmployeeDto employeeDetails)
            throws ResourceNotFoundException
    {
        Optional<EmployeeDto> employee = employeeService.findEmployeeById(employeeId);
        employee.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + employeeId));
        employee.get().setEmail(employeeDetails.getEmail());
        employee.get().setLastName(employeeDetails.getLastName());
        employee.get().setFirstName(employeeDetails.getFirstName());
        final EmployeeDto updatedEmployee = employeeService.updateEmployee(employee.get());
        if ( null != employee )
        {
            return ResponseEntity.ok(updatedEmployee);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @io.swagger.annotations.ApiOperation(value = "Delete a employee")
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully delete employee"),
            @io.swagger.annotations.ApiResponse(code = 204, message = "No delete employee"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Employee not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map< String, Boolean >> deleteEmployee(
            @io.swagger.annotations.ApiParam(value = "Employee Id from which employee object will delete from database table", required = true)
            @PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException
    {
        Optional<EmployeeDto> employee = employeeService.findEmployeeById(employeeId);
        employee.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + employeeId));
        Boolean deleted = employeeService.deleteEmployee(employee.get());
        Map < String, Boolean > response = new HashMap< >();
        if ( deleted) {
            response.put("deleted", Boolean.TRUE);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else
        {
            response.put("No deleted", Boolean.FALSE);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }
}