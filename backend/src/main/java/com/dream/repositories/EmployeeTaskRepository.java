package com.dream.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.EmployeeTask;

public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask, Long> {

	Page<EmployeeTask> findByEmployeeIdAndRegisteredDateBetween(long employeeId, Date time, Date time2,
			Pageable pageable);

	Page<EmployeeTask> findByEmployeeId(long employeeId, Pageable pageable);

	Page<EmployeeTask> findByRegisteredDateBetween(long employeeId, Date time, Date time2, Pageable pageable);

}
