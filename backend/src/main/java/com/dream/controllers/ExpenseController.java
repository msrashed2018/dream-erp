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

import com.dream.models.Expense;
import com.dream.services.ExpenseService;

@CrossOrigin(origins = "*")
@RestController
public class ExpenseController {
	@Autowired
	private ExpenseService expenseService;

	@GetMapping("/expenses/totalPrice")
	public ResponseEntity<Long> getExpensesTotalprice(@RequestParam(name = "dateFrom", required = false) String dateFrom,
			@RequestParam(name = "dateTo", required = false) String dateTo,
			@RequestParam(name = "expenseTypeId", defaultValue = "0",required = false) long expenseTypeId) {
		try {
			return new ResponseEntity<Long>( expenseService.getExpensesTotalPrice(dateFrom, dateTo, expenseTypeId), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	@GetMapping("/expenses")
	public Page<Expense> retrieveAllExpenses(@RequestParam(name = "dateFrom", required = false) String dateFrom,
			@RequestParam(name = "dateTo", required = false) String dateTo,
			@RequestParam(name = "expenseTypeId", defaultValue = "0",required = false) long expenseTypeId, Pageable pageable) {
		try {
			return expenseService.getExpenses(dateFrom, dateTo, expenseTypeId, pageable);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@GetMapping("/expenses/{expenseId}")
	public Expense retrieveExpenseById(@PathVariable long expenseId) {
		return expenseService.getExpenseById(expenseId);
	}

	@DeleteMapping("/expenses/{expenseId}")
	public void deleteExpense(@PathVariable long expenseId) {
		expenseService.deleteExpense(expenseId);
	}

	@PostMapping("/expenses")
	public ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense) {

		Expense createdExpense = expenseService.createExpense(expense);
		return new ResponseEntity<Expense>(createdExpense, HttpStatus.OK);

	}

	@PutMapping("/expenses/{expenseId}")
	public ResponseEntity<Expense> updateExpense(@PathVariable int expenseId, @Valid @RequestBody Expense expense) {
		Expense updatedExpense = expenseService.updateExpense(expenseId, expense);
		return new ResponseEntity<Expense>(updatedExpense, HttpStatus.OK);
	}
}
