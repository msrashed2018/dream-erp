package com.dream.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

	Page<Bill> findBySchoolIdAndToContainingAndRegisteredDateBetween(long schoolId, String to, Date time, Date time2,
			Pageable pageable);

	Page<Bill> findByToContainingAndRegisteredDateBetween(String to, Date time, Date time2, Pageable pageable);

	Page<Bill> findByToContaining(String to, Pageable pageable);

}
