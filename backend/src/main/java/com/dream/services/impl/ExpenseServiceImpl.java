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

import com.dream.models.Expense;
import com.dream.repositories.ExpenseRepository;
import com.dream.services.ExpenseService;
import com.dream.utils.DateUtils;

/**
 * @author mohamedsalah
 *
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {
	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public Page<Expense> getExpenses(String dateFrom, String dateTo, long expenseTypeId, Pageable pageable)
			throws ParseException {

		if (expenseTypeId != 0l) {

			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return expenseRepository.findByRegisteredDateBetweenAndExpenseTypeId(start.getTime(), end.getTime(),
						expenseTypeId, pageable);
			} else {
				return expenseRepository.findByExpenseTypeId(expenseTypeId, pageable);
			}

		} else {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return expenseRepository.findByRegisteredDateBetween(start.getTime(), end.getTime(), pageable);
			} else {
				return expenseRepository.findAll(pageable);
			}
		}

	}

	@Override
	public Expense getExpenseById(long id) {
		Optional<Expense> expense = expenseRepository.findById(id);
		if (!expense.isPresent()) {
			throw new ResourceNotFoundException("ExpenseId " + id + " not found");
		}
		return expense.get();
	}

	@Override
	public Expense createExpense(Expense expense) {
		return expenseRepository.save(expense);
	}

	@Override
	public Expense updateExpense(long id, Expense expense) {
		Optional<Expense> existingExpense = expenseRepository.findById(id);
		if (!existingExpense.isPresent()) {
			throw new ResourceNotFoundException("ExpenseId " + id + " not found");
		}
		existingExpense.get().setExpenseType(expense.getExpenseType());
		existingExpense.get().setValue(expense.getValue());
		existingExpense.get().setNotes(expense.getNotes());
		return expenseRepository.save(existingExpense.get());
	}

	@Override
	public void deleteExpense(long id) {
		if (!expenseRepository.existsById(id)) {
			throw new ResourceNotFoundException("ExpenseId " + id + " not found");
		}
		expenseRepository.deleteById(id);

	}

	@Override
	public long getExpensesTotalPrice(String dateFrom, String dateTo, long expenseTypeId) throws ParseException {
		if (expenseTypeId != 0l) {

			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return expenseRepository.getTotalPricesByRegisteredDateBetweenAndExpenseTypeId(start.getTime(),
						end.getTime(), expenseTypeId);
			} else {
				return expenseRepository.getTotalPricesByExpenseTypeId(expenseTypeId);
			}

		} else {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return expenseRepository.getTotalPricesByRegisteredDateBetween(start.getTime(), end.getTime());
			} else {
				return expenseRepository.getTotalPrices();
			}
		}
	}

}
