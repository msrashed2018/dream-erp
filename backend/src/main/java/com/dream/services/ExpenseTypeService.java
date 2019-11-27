package com.dream.services;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dream.models.ExpenseType;

public interface ExpenseTypeService {
	
	List<ExpenseType> getAllExpenseTypes();
	
	Page<ExpenseType> getExpenseTypes(Pageable pageable);
	
	ExpenseType getExpenseTypeById(long id);
	
	ExpenseType createExpenseType(ExpenseType ExpenseType);
	
	ExpenseType updateExpenseType(long id, ExpenseType ExpenseType);
	
	void deleteExpenseType(long id);
}
