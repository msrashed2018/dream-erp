package com.dream.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dream.models.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	Page<Expense> findByRegisteredDateBetweenAndExpenseTypeId(Date time, Date time2, long expenseTypeId,
			Pageable pageable);

	Page<Expense> findByExpenseTypeId(long expenseTypeId, Pageable pageable);

	Page<Expense> findByRegisteredDateBetween(Date time, Date time2, Pageable pageable);
	
	@Query(value = "SELECT sum(s.value) FROM Expense s Where s.expenseType.id=:expenseTypeId AND  s.registeredDate > :registeredDateFrom AND  s.registeredDate < :registeredDateTo")
	public Long getTotalPricesByRegisteredDateBetweenAndExpenseTypeId(Date registeredDateFrom, Date registeredDateTo, long expenseTypeId);
	
	@Query(value = "SELECT sum(s.value) FROM Expense s Where s.expenseType.id=:expenseTypeId")
	public Long getTotalPricesByExpenseTypeId(long expenseTypeId);
	
	@Query(value = "SELECT sum(s.value) FROM Expense s Where  s.registeredDate > :registeredDateFrom AND  s.registeredDate < :registeredDateTo")
	public Long getTotalPricesByRegisteredDateBetween(Date registeredDateFrom, Date registeredDateTo);
	
	@Query(value = "SELECT sum(s.value) FROM Expense s ")
	public Long getTotalPrices();

}
