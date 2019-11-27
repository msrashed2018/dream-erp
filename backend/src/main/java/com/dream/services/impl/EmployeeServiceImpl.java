/**
 * 
 */
package com.dream.services.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.models.Employee;
import com.dream.models.EmployeeTask;
import com.dream.models.SalaryType;
import com.dream.repositories.EmployeeRepository;
import com.dream.repositories.EmployeeTaskRepository;
import com.dream.services.EmployeeService;
import com.dream.utils.DateUtils;

/**
 * @author mohamedsalah
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeTaskRepository employeeTaskRepository;

	@Override
	public Page<Employee> getEmployees(long occupationId, SalaryType salaryType, String nameContaining,
			Pageable pageable) {

		if (occupationId != 0 && salaryType != null) {
			return employeeRepository.findByOccupationIdAndSalaryTypeAndNameContaining(occupationId, salaryType,
					nameContaining, pageable);
		} else if (occupationId != 0 && salaryType == null) {
			return employeeRepository.findByOccupationIdAndNameContaining(occupationId, nameContaining, pageable);
		} else if (occupationId == 0 && salaryType != null) {
			return employeeRepository.findBySalaryTypeAndNameContaining(salaryType, nameContaining, pageable);
		} else {
			return employeeRepository.findByNameContaining(occupationId, salaryType, nameContaining, pageable);
		}
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (!employee.isPresent()) {
			throw new ResourceNotFoundException("EmployeeId " + id + " not found");
		}
		return employee.get();
	}

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(long id, Employee employee) {
		Optional<Employee> existingEmployee = employeeRepository.findById(id);
		if (!existingEmployee.isPresent()) {
			throw new ResourceNotFoundException("EmployeeId " + id + " not found");
		}

		existingEmployee.get().setAddress(employee.getAddress());
		existingEmployee.get().setEmail(employee.getEmail());
		existingEmployee.get().setMobileNo1(employee.getMobileNo1());
		existingEmployee.get().setMobileNo2(employee.getMobileNo2());
		existingEmployee.get().setName(employee.getName());
		existingEmployee.get().setNotes(employee.getNotes());
		existingEmployee.get().setOccupation(employee.getOccupation());
		existingEmployee.get().setSalary(employee.getSalary());
		existingEmployee.get().setSalaryType(employee.getSalaryType());

		return employeeRepository.save(existingEmployee.get());
	}

	@Override
	public void deleteEmployee(long id) {
		if (!employeeRepository.existsById(id)) {
			throw new ResourceNotFoundException("EmployeeId " + id + " not found");
		}
		employeeRepository.deleteById(id);
	}

	@Override
	public Page<EmployeeTask> getEmployeeTasks(long employeeId, String dateFrom, String dateTo, Pageable pageable)
			throws ParseException {
		if (dateFrom != null && dateTo != null) {

			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			DateUtils.formatDates(start, end, dateFrom, dateTo);
			if (employeeId != 0l) {
				return employeeTaskRepository.findByEmployeeIdAndRegisteredDateBetween(employeeId, start.getTime(),
						end.getTime(), pageable);
			} else {
				return employeeTaskRepository.findByRegisteredDateBetween(employeeId, start.getTime(), end.getTime(),
						pageable);
			}

		} else {
			if (employeeId != 0l) {
				return employeeTaskRepository.findByEmployeeId(employeeId, pageable);
			} else {
				return employeeTaskRepository.findAll(pageable);
			}

		}

	}

	@Override
	public EmployeeTask addEmployeeTask(long employeeId, EmployeeTask employeeTask) {
		Optional<Employee> existingEmployee = employeeRepository.findById(employeeId);
		if (!existingEmployee.isPresent()) {
			throw new ResourceNotFoundException("EmployeeId " + employeeId + " not found");
		}

		employeeTask.setEmployee(existingEmployee.get());

		return employeeTaskRepository.save(employeeTask);
	}

	@Override
	public EmployeeTask updateEmployeeTask(long employeeTaskId, EmployeeTask employeeTask) {
		Optional<EmployeeTask> existingEmployeeTask = employeeTaskRepository.findById(employeeTaskId);
		if (!existingEmployeeTask.isPresent()) {
			throw new ResourceNotFoundException("EmployeeTaskId " + employeeTaskId + " not found");
		}
		existingEmployeeTask.get().setAmount(employeeTask.getAmount());
		existingEmployeeTask.get().setSize(employeeTask.getSize());
		existingEmployeeTask.get().setNotes(employeeTask.getNotes());
		existingEmployeeTask.get().setTask(employeeTask.getTask());
		return employeeTaskRepository.save(existingEmployeeTask.get());
	}

	@Override
	public void deleteEmployeeTask(long employeeTaskId) {
		if (!employeeTaskRepository.existsById(employeeTaskId)) {
			throw new ResourceNotFoundException("employeeTaskId " + employeeTaskId + " not found");
		}
		employeeTaskRepository.deleteById(employeeTaskId);
	}

}
