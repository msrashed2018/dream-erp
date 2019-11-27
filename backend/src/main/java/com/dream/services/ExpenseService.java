package com.dream.services;

import org.springframework.data.domain.Pageable;

import java.text.ParseException;

import org.springframework.data.domain.Page;

import com.dream.models.Expense;
import com.dream.models.ExpenseType;

public interface ExpenseService {
	
	Page<Expense> getExpenses(String dateFrom, String dateTo, long expenseTypeId,Pageable pageable) throws ParseException;
	
	Expense getExpenseById(long id);
	
	Expense createExpense(Expense Expense);
	
	Expense updateExpense(long id, Expense Expense);
	
	void deleteExpense(long id);
	
	long getExpensesTotalPrice(String dateFrom, String dateTo, long expenseTypeId) throws ParseException ;
}
