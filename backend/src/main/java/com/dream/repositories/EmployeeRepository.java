package com.dream.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.Employee;
import com.dream.models.SalaryType;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Page<Employee> findByOccupationIdAndSalaryTypeAndNameContaining(long occupationId, SalaryType salaryType,
			String nameContaining, Pageable pageable);

	Page<Employee> findByOccupationIdAndNameContaining(long occupationId, String nameContaining, Pageable pageable);

	Page<Employee> findBySalaryTypeAndNameContaining(SalaryType salaryType, String nameContaining, Pageable pageable);

	Page<Employee> findByNameContaining(long occupationId, SalaryType salaryType, String nameContaining, Pageable pageable);

}
