package com.dream.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.dream.models.ExpenseType;
import com.dream.services.ExpenseTypeService;

@CrossOrigin(origins = "*")
@RestController
public class ExpenseTypeController {
	@Autowired
	private ExpenseTypeService expenseTypeService;

	@GetMapping("/expenseTypes/all")
	public List<ExpenseType> retrieveAllExpenseTypes() {
		return expenseTypeService.getAllExpenseTypes();
	}
	
	@GetMapping("/expenseTypes")
	public Page<ExpenseType> retrieveExpenseTypes(Pageable pageable) {
		return expenseTypeService.getExpenseTypes(pageable);
	}

	@GetMapping("/expenseTypes/{expenseTypeId}")
	public ExpenseType retrieveExpenseTypeById(@PathVariable long expenseTypeId) {
		return expenseTypeService.getExpenseTypeById(expenseTypeId);
	}

	@DeleteMapping("/expenseTypes/{expenseTypeId}")
	public void deleteExpenseType(@PathVariable long expenseTypeId) {
		expenseTypeService.deleteExpenseType(expenseTypeId);
	}

	@PostMapping("/expenseTypes")
	public ResponseEntity<ExpenseType> createExpenseType(@Valid @RequestBody ExpenseType expenseType) {

		ExpenseType createdExpenseType = expenseTypeService.createExpenseType(expenseType);
		return new ResponseEntity<ExpenseType>(createdExpenseType, HttpStatus.OK);

	}

	@PutMapping("/expenseTypes/{expenseTypeId}")
	public ResponseEntity<ExpenseType> updateExpenseType(@PathVariable int expenseTypeId, @Valid @RequestBody ExpenseType expenseType) {
		ExpenseType updatedExpenseType = expenseTypeService.updateExpenseType(expenseTypeId, expenseType);
		return new ResponseEntity<ExpenseType>(updatedExpenseType, HttpStatus.OK);
	}
}
