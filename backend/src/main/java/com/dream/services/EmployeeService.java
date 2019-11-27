package com.dream.services;

import org.springframework.data.domain.Pageable;

import java.text.ParseException;

import org.springframework.data.domain.Page;

import com.dream.models.Employee;
import com.dream.models.EmployeeTask;
import com.dream.models.SalaryType;

public interface EmployeeService {
	
	Page<Employee> getEmployees(long occupationId, SalaryType salaryType, String nameContaining, Pageable pageable);
	
	Employee getEmployeeById(long id);
	
	Employee createEmployee(Employee Employee);
	
	Employee updateEmployee(long id, Employee Employee);
	
	void deleteEmployee(long id);
	
	Page<EmployeeTask> getEmployeeTasks(long employeeId, String dateFrom, String dateTo, Pageable pageable) throws ParseException;
	
	EmployeeTask addEmployeeTask(long employeeId, EmployeeTask employeeTask);
	
	EmployeeTask updateEmployeeTask(long employeeTaskId, EmployeeTask employeeTask);
	
	void deleteEmployeeTask(long employeeTaskId);
	
}
