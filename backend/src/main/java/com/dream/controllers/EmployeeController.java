package com.dream.controllers;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dream.models.Employee;
import com.dream.models.EmployeeTask;
import com.dream.models.SalaryType;
import com.dream.services.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public Page<Employee> retrieveEmployees(@RequestParam(name = "occupationId", defaultValue = "0", required = false) long occupationId,
			@RequestParam(name = "salaryType", required = false) SalaryType salaryType,
			@RequestParam(name = "name", defaultValue = "", required = false) String nameContaining,
			Pageable pageable) {
		return employeeService.getEmployees(occupationId, salaryType, nameContaining, pageable);
	}

	@GetMapping("/employees/{employeeId}")
	public Employee retrieveEmployeeById(@PathVariable long employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}

	@DeleteMapping("/employees/{employeeId}")
	public void deleteEmployee(@PathVariable long employeeId) {
		employeeService.deleteEmployee(employeeId);
	}

	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {

		Employee createdEmployee = employeeService.createEmployee(employee);
		return new ResponseEntity<Employee>(createdEmployee, HttpStatus.OK);

	}

	@PutMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long employeeId,
			@Valid @RequestBody Employee employee) {
		Employee updatedEmployee = employeeService.updateEmployee(employeeId, employee);
		return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
	}
	
	@GetMapping("/employees/tasks")
	public Page<EmployeeTask> retrieveEmployeeTasks(@RequestParam(name = "employeeId", required = false) long employeeId,
			@RequestParam(name = "dateFrom", required = false) String dateFrom,
			@RequestParam(name = "dateTo", required = false) String dateTo,
			Pageable pageable) {
		try {
			return employeeService.getEmployeeTasks(employeeId, dateFrom, dateTo, pageable);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@PostMapping("/employees/{employeeId}/tasks")
	public ResponseEntity<EmployeeTask> addEmployeeTask(@PathVariable long employeeId, @Valid @RequestBody EmployeeTask employeeTask) {
		EmployeeTask createdEmployeeTask = employeeService.addEmployeeTask(employeeId, employeeTask);
		return new ResponseEntity<EmployeeTask>(createdEmployeeTask, HttpStatus.OK);
	}
	
	@PutMapping("/employees/tasks/{employeeTaskId}")
	public ResponseEntity<EmployeeTask> updateEmployeeTask(@PathVariable long employeeTaskId, @Valid @RequestBody EmployeeTask employeeTask) {
		EmployeeTask updatedEmployeeTask = employeeService.updateEmployeeTask(employeeTaskId, employeeTask);
		return new ResponseEntity<EmployeeTask>(updatedEmployeeTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/employees/tasks/{employeeTaskId}")
	public void deleteEmployeeTask(@PathVariable long employeeTaskId) {
		employeeService.deleteEmployeeTask(employeeTaskId);
	}
	
}
